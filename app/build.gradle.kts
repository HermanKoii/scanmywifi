import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
}

// Load signing properties
val signingPropertiesFile = rootProject.file("signing.properties")
val signingProperties = Properties()
if (signingPropertiesFile.exists()) {
    signingProperties.load(signingPropertiesFile.inputStream())
}

android {
    compileSdk = 33
    
    defaultConfig {
        applicationId = "com.example.wifiscanner"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
    }

    signingConfigs {
        create("release") {
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
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Add your dependencies here
}