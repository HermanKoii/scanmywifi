import java.util.Properties

plugins {
    id(\"com.android.application\")
    id(\"kotlin-android\")
}

// Load signing properties
val signingPropertiesFile = rootProject.file(\"signing.properties\")
val signingProperties = Properties()
if (signingPropertiesFile.exists()) {
    signingProperties.load(signingPropertiesFile.inputStream())
}

android {
    compileSdk = 33
    
    defaultConfig {
        applicationId = "com.wifiscanner"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        // Ensure BuildConfig generation
        buildConfigField("boolean", "DEBUG", "true")
    }

    signingConfigs {
        create(\"release\") {
            // Robust signing config handling
            val storeFile = signingProperties[\"storeFile\"] as String?
            val storePassword = signingProperties[\"storePassword\"] as String?
            val keyAlias = signingProperties[\"keyAlias\"] as String?
            val keyPassword = signingProperties[\"keyPassword\"] as String?

            // Only set signing config if all required properties are present
            if (storeFile != null && storePassword != null && keyAlias != null && keyPassword != null) {
                storeFile(file(storeFile))
                storePassword(storePassword)
                keyAlias(keyAlias)
                keyPassword(keyPassword)
            }
        }
    }

    buildTypes {
        getByName(\"release\") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile(\"proguard-android-optimize.txt\"),
                \"proguard-rules.pro\"
            )
            signingConfig = signingConfigs.getByName(\"release\")
            
            // Ensure debug vs release differentiation
            applicationIdSuffix = \".release\"
            buildConfigField("boolean", "DEBUG", "false")
        }

        getByName(\"debug\") {
            isMinifyEnabled = false
            applicationIdSuffix = \".debug\"
            buildConfigField("boolean", "DEBUG", "true")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = \"11\"
    }

    // Enable BuildConfig generation
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    // Basic Android dependencies
    implementation(\"androidx.core:core-ktx:1.9.0\")
    implementation(\"androidx.appcompat:appcompat:1.6.1\")
    implementation(\"com.google.android.material:material:1.8.0\")

    // Testing dependencies
    testImplementation(\"junit:junit:4.13.2\")
    androidTestImplementation(\"androidx.test.ext:junit:1.1.5\")
    androidTestImplementation(\"androidx.test.espresso:espresso-core:3.5.1\")
}