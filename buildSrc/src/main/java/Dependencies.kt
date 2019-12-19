object Version {
    const val GRADLE = "3.5.1"
    const val GOOGLE_SERVICE = "4.3.2"

    const val KOTLIN = "1.3.50"
    const val NAVIGATION = "2.1.0"

    const val JUNIT = "1.1.1"
    const val ESPRESSO = "3.2.0"

    const val MATERIAL = "1.0.0"
    const val CONSTRAINT_LAYOUT = "1.1.3"

    const val LIFECYCLE = "2.1.0"

    const val RETROFIT = "2.6.1"
    const val LOGGING_INTERCEPTOR = "3.14.2"

    const val KOIN = "2.0.1"

    const val COROUTINE = "1.2.1"

    const val RX_ANDROID = "2.1.1"
    const val RX_KOTLIN = "2.4.0"

    const val GLIDE = "4.9.0"

    const val ANALYTICS = "17.2.0"
    const val ADS = "18.3.0"

    const val PAGING = "2.1.0"

    const val ROOM = "2.2.2"

    const val CORE_KTX = "1.1.0"

    const val SHIMMER = "0.5.0"
}

object ProjectDependencies {
    const val GRADLE = "com.android.tools.build:gradle:${Version.GRADLE}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val NAV_SAFE_ARG =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.NAVIGATION}"
    const val GOOGLE_SERVICE = "com.google.gms:google-services:${Version.GOOGLE_SERVICE}"
}

object AndroidSettings {
    const val COMPILE_SDK = 29
    const val BUILD_TOOLS = "29.0.2"
    const val MIN_SDK = 23
    const val TARGET_SDK = 29
    const val APP_ID = "com.beok.gitbeoktree"
}

object Release {
    const val VERSION_CODE = 9
    const val VERSION_NAME = "1.0.0"
}

object Libraries {
    const val KOTLIN_STDLIB_JDK =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.KOTLIN}"
    const val RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val JUNIT = "androidx.test.ext:junit:${Version.JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Version.ESPRESSO}"
    const val KOTLIN_TEST_JUNIT =
        "org.jetbrains.kotlin:kotlin-test-junit:${Version.KOTLIN}"

    const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"

    const val LIFECYCLE_EXT = "androidx.lifecycle:lifecycle-extensions:${Version.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}"

    const val NAVIGATION_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION}"
    const val NAVIGATION_SAFEARGS = "androidx.navigation.safeargs.kotlin"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Version.RETROFIT}"
    const val LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${Version.LOGGING_INTERCEPTOR}"

    const val KOIN_CORE = "org.koin:koin-core:${Version.KOIN}"
    const val KOIN_VIEWMODEL = "org.koin:koin-androidx-viewmodel:${Version.KOIN}"

    const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINE}"

    const val RX_ANDROID = "io.reactivex.rxjava2:rxandroid:${Version.RX_ANDROID}"
    const val RX_KOTLIN = "io.reactivex.rxjava2:rxkotlin:${Version.RX_KOTLIN}"

    const val GLIDE = "com.github.bumptech.glide:glide:${Version.GLIDE}"

    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics:${Version.ANALYTICS}"
    const val FIREBASE_ADS = "com.google.firebase:firebase-ads:${Version.ADS}"

    const val PAGING = "androidx.paging:paging-runtime:${Version.PAGING}"

    const val ROOM = "androidx.room:room-runtime:${Version.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Version.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Version.ROOM}"

    const val CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX}"

    const val SHIMMER = "com.facebook.shimmer:shimmer:${Version.SHIMMER}"
}