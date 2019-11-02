buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        BuildScript.run {
            classpath(GRADLE)
            classpath(KOTLIN_GRADLE_PLUGIN)
            classpath(NAV_SAFE_ARG)
            classpath(GOOGLE_SERVICE)
        }
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
