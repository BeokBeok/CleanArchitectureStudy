plugins {
    id("com.android.library")
    id("kotlin-android")
    id(Navigation.SAFEARGS)
}

android {
    App.run {
        compileSdkVersion(COMPILE_SDK)
        buildToolsVersion(BUILD_TOOLS)

        defaultConfig {
            minSdkVersion(MIN_SDK)
            targetSdkVersion(TARGET_SDK)
        }
    }

}

dependencies {
    Navigation.run {
        implementation(FRAGMENT)
        implementation(UI)
    }
}
