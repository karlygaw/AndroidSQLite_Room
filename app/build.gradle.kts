plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "kz.narxoz.android1"
    compileSdk = 34

    defaultConfig {
        applicationId = "kz.narxoz.android1"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Ensure this matches the Compose version you're using
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Jetpack Compose Dependencies
    implementation("androidx.compose.ui:ui:1.4.3") // Core UI
    implementation("androidx.compose.material3:material3:1.0.1") // Material3
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3") // UI Tooling
    implementation("androidx.navigation:navigation-compose:2.5.3") // Navigation for Compose
    implementation("androidx.compose.foundation:foundation:1.4.3") // Foundation components

    // Additional AndroidX Dependencies
    implementation(libs.androidx.core.ktx) // Core KTX
    implementation(libs.androidx.lifecycle.runtime.ktx) // Lifecycle KTX
    implementation(libs.androidx.activity.compose) // Activity Compose

    // UI Testing and Debugging Dependencies
    androidTestImplementation(platform(libs.androidx.compose.bom)) // BOM for Compose testing
    androidTestImplementation(libs.androidx.ui.test.junit4) // UI Test JUnit4
    debugImplementation(libs.androidx.ui.tooling) // UI Tooling for debugging
    debugImplementation(libs.androidx.ui.test.manifest) // Manifest for UI testing

    // Other Dependencies (JUnit, Espresso)
    testImplementation(libs.junit) // JUnit for unit testing
    androidTestImplementation(libs.androidx.junit) // JUnit for Android testing
    androidTestImplementation(libs.androidx.espresso.core) // Espresso for UI testing
}
