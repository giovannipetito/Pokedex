plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.satispay.pokedex"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.satispay.pokedex"
        minSdk = 33
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    /*
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }
    */
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // ksp
    implementation(libs.symbol.processing.api)

    // Permissions
    implementation(libs.accompanist.permissions)

    // DataStore Preferences
    implementation(libs.androidx.datastore.preferences.android)

    // Navigation Compose
    implementation(libs.androidx.navigation.compose)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Serialization: Json
    implementation(libs.kotlinx.serialization.json)

    // Serialization: Gson
    implementation(libs.gson)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava3)
    implementation(libs.converter.scalars)

    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Reactive Extensions
    implementation(libs.rxjava)
    implementation(libs.rxkotlin)
    implementation(libs.rxandroid)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Coil
    implementation(libs.coil.compose)

    // Dependency Injection - Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    // implementation(libs.androidx.hilt.work)
    ksp(libs.androidx.hilt.compiler)
    ksp(libs.hilt.android.compiler)

    // Room Database
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.rxjava3)

    // Paging 3.0
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.rxjava3)
    implementation(libs.androidx.paging.runtime.ktx)

    // Splash API
    implementation(libs.androidx.core.splashscreen)

    // Lottie
    implementation(libs.lottie.compose)

    // Adaptive Navigation
    implementation(libs.adaptive.navigation)
    implementation(libs.material3.adaptive.navigation.suite)
}