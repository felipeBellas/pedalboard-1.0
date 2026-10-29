plugins { id("com.android.application"); id("org.jetbrains.kotlin.android") }

android {
    namespace = "app.pedalboard"
    compileSdk = 34
    defaultConfig {
        applicationId = "app.pedalboard"
        minSdk = 26; targetSdk = 34
        versionCode = 1; versionName = "1.0.0"
        ndk { abiFilters += "arm64-v8a" }
        externalNativeBuild { cmake {
            cppFlags += listOf("-std=c++17", "-O3", "-ffast-math")
            arguments += "-DANDROID_STL=c++_shared"
        } }
    }
    buildFeatures { prefab = true }
    externalNativeBuild { cmake { path = file("src/main/cpp/CMakeLists.txt") } }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
    buildTypes { release { isMinifyEnabled = true } }
}

dependencies { implementation("com.google.oboe:oboe:1.9.0") }
