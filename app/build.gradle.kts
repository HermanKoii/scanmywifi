import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
}

// Load signing properties
<<<<<<< HEAD
val signingPropertiesFile = rootProject.file("signing.properties")
val signingProperties = Properties()
if (signingPropertiesFile.exists()) {
    signingProperties.load(signingPropertiesFile.inputStream())
=======
val signingPropsFile = rootProject.file("signing.properties")
val signingProps = Properties()
if (signingPropsFile.exists()) {
    signingProps.load(signingPropsFile.inputStream())
>>>>>>> pr-2-relayrelayrelay-scanmywifi
}

android {
    compileSdk = 33
    
    defaultConfig {
<<<<<<< HEAD
        applicationId = "com.example.wifiscanner"
=======
        applicationId = "com.wifiscanner"
>>>>>>> pr-2-relayrelayrelay-scanmywifi
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
<<<<<<< HEAD
=======

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
>>>>>>> pr-2-relayrelayrelay-scanmywifi
    }

    signingConfigs {
        create("release") {
<<<<<<< HEAD
            // Check if signing properties are available
            val storeFile = signingProperties["storeFile"] as String?
            val storePassword = signingProperties["storePassword"] as String?
            val keyAlias = signingProperties["keyAlias"] as String?
            val keyPassword = signingProperties["keyPassword"] as String?

            if (storeFile != null && storePassword != null && keyAlias != null && keyPassword != null) {
                storeFile(file(storeFile))
                storePassword(storePassword)
                keyAlias(keyAlias)
                keyPassword(keyPassword)
            }
=======
            // Use properties from signing.properties
            storeFile = file(signingProps["storeFile"] as String? ?: "")
            storePassword = signingProps["storePassword"] as String? ?: ""
            keyAlias = signingProps["keyAlias"] as String? ?: ""
            keyPassword = signingProps["keyPassword"] as String? ?: ""
>>>>>>> pr-2-relayrelayrelay-scanmywifi
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
<<<<<<< HEAD
=======
            isShrinkResources = true
>>>>>>> pr-2-relayrelayrelay-scanmywifi
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
<<<<<<< HEAD
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
=======

        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
>>>>>>> pr-2-relayrelayrelay-scanmywifi
    }
}

dependencies {
<<<<<<< HEAD
    // Add your dependencies here
=======
    // Basic Android dependencies
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")

    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
>>>>>>> pr-2-relayrelayrelay-scanmywifi
}