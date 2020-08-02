plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    maven("https://plugins.gradle.org/m2/")
    maven("https://jitpack.io")
    google()
    jcenter()
    mavenCentral()
}

object Plugins {
    object Version {
        const val googleServices = "4.3.3"
        const val kotlin = "1.3.72"
        const val androidGradle: String = "4.0.1"
        const val navigation: String = "2.3.0"
        const val daggerHiltAndroid: String = "2.28-alpha"
        const val ktLint = "9.3.0"
        const val detekt = "1.10.0"
        const val jacoco = "0.8.5"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val androidGradle = "com.android.tools.build:gradle:${Version.androidGradle}"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
    const val daggerHilt =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.daggerHiltAndroid}"
    const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:${Version.ktLint}"
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Version.detekt}"
    const val jacoco = "org.jacoco:org.jacoco.core:${Version.jacoco}"
    const val googleServices = "com.google.gms:google-services:${Version.googleServices}"
}

dependencies {
    implementation(Plugins.kotlin)
    implementation(Plugins.googleServices)
    implementation(Plugins.androidGradle)
    implementation(Plugins.navigationSafeArgs)
    implementation(Plugins.daggerHilt)
    implementation(Plugins.ktLint)
    implementation(Plugins.detekt)
    implementation(Plugins.jacoco)
}
