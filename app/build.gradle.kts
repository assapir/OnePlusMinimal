plugins {
    id("com.android.application")
}

android {
    namespace = "af.ass.watchface.minimal24"
    compileSdk = 35

    defaultConfig {
        applicationId = "af.ass.watchface.minimal24"
        minSdk = 34
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}
