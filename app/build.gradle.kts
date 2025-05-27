plugins {
//    id("com.android.application") version "8.10.0"
//    // Kotlin + Compose + KSP
//    id("org.jetbrains.kotlin.android") version "2.0.21"
//    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
//    id("com.google.devtools.ksp") version "2.0.21-1.0.28"

    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("org.jetbrains.kotlin.plugin.compose")
//    id("org.jetbrains.kotlin.kapt")           // <— for Hilt’s annotation processor
    id("com.google.devtools.ksp")             // <— for Room’s processor
    id("com.google.dagger.hilt.android")      // <— Hilt Gradle plugin (no version here)

//    id("com.android.application")          version "8.10.0"
//    id("org.jetbrains.kotlin.android")     version "2.0.21"
//    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
//    id("com.google.devtools.ksp")          version "2.0.21-1.0.28"
//    id("com.google.dagger.hilt.android")   version "2.48"

//    // Android Gradle Plugin
//    id("com.android.application")           version "8.10.0"     apply false
//
//    // Kotlin (1.9.x)
//    id("org.jetbrains.kotlin.android")      version "1.9.24"     apply false
//    id("org.jetbrains.kotlin.plugin.compose") version "1.9.24"    apply false
//
//    // KSP1 for both Hilt & Room
//    id("com.google.devtools.ksp")           version "1.9.24-1.0.30" apply false
//
//    // Hilt Gradle plugin
//    id("com.google.dagger.hilt.android")    version "2.48"       apply false


}

android {
    namespace = "com.alexteodorovici.urlshortener"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.alexteodorovici.urlshortener"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true

        // enable ViewBinding (for ActivityMainBinding.inflate)
        viewBinding = true

        // enable DataBinding if you have <layout> tags in XML
//        dataBinding = true
    }

    composeOptions {
        // keep your Compose compiler extension up to date
//        kotlinCompilerExtensionVersion = "1.4.7"
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core
    implementation("androidx.core:core-ktx:1.16.0")

    // AndroidX Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.0")

    // Activity & Compose
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation(platform("androidx.compose:compose-bom:2025.05.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // UI tests & tooling
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.05.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // AppCompat
    implementation("androidx.appcompat:appcompat:1.7.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-compiler:2.48")
//    kapt("com.google.dagger:hilt-compiler:2.48")

    // Room
    implementation("androidx.room:room-runtime:2.7.1")
    implementation("androidx.room:room-ktx:2.7.1")
    ksp("androidx.room:room-compiler:2.7.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}