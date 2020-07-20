plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}


repositories {
    maven { url = uri("https://jitpack.io") }
    google()
    jcenter()
    mavenCentral()
}

object Plugins {
    object Version {
        const val kotlin = "1.3.72"
        const val androidGradle: String = "4.0.1"
        const val navigation: String = "2.3.0"
        const val daggerHiltAndroid: String = "2.28-alpha"
    }

    const val kotlin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val androidGradle: String = "com.android.tools.build:gradle:${Version.androidGradle}"
    const val navigationSafeArgs: String =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
    const val daggerHilt: String =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.daggerHiltAndroid}"
}

val deps =
    listOf(Plugins.kotlin, Plugins.androidGradle, Plugins.navigationSafeArgs, Plugins.daggerHilt)

dependencies {
    implementation(Plugins.kotlin)
    implementation(Plugins.androidGradle)
    implementation(Plugins.navigationSafeArgs)
    implementation(Plugins.daggerHilt)
}


