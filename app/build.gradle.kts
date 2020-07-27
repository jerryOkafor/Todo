import Dependencies.AndroidTest
import Dependencies.AndroidX
import Dependencies.Desugaring
import Dependencies.Hilt
import Dependencies.Kotlin
import Dependencies.Navigation
import Dependencies.ProjectModule.core
import Dependencies.ProjectModule.timeline
import Dependencies.Retrofit
import Dependencies.Test
import Dependencies.Utils
import Dependencies.View

//https://developer.android.com/jetpack/androidx/migrate/artifact-mappings
//https://stackoverflow.com/questions/62712430/import-kotlinx-android-synthetic-failed-android-studio-doesnt-find-it-but-i-c
//https://issuetracker.google.com/issues/145888144
//https://developer.android.com/studio/write/java8-support

plugins {
    applyAndroidApplication
    applyKotlinAndroid
    applyKotlinAndroidExtension
    applyKotlinKapt
    applyDaggerHilt
}

android {
    compileSdkVersion(Config.Version.compileSdkVersion)
    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdkVersion(Config.Version.minSdkVersion)
        targetSdkVersion(Config.Version.targetSdkVersion)
        versionCode = Config.Version.versionCode
        versionName = Config.Version.versionName
        testInstrumentationRunner = Config.Android.testInstrumentationRunner
    }

    androidExtensions {
        isExperimental = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        named("debug") {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }

        named("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }

    }

    @Suppress("UnstableApiUsage")
    compileOptions {
        // Flag to enable support for the new language APIs
        coreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dynamicFeatures = mutableSetOf(":createTaskFeature", ":dataReportFeature")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    coreLibraryDesugaring(Desugaring.desugaring)

    /**Modules*/
    implementation(project(core))
    implementation(project(timeline))

    implementation(Kotlin.stdlib)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifeCycleCommon)
    implementation(AndroidX.liveData)
    implementation(AndroidX.viewModel)
    implementation(View.material)
    implementation(View.constraintLayout)
    implementation(View.calenderView)

    /**Utils*/
    api(Utils.timber)
    implementation(Utils.playCore)
    implementation(Utils.playCoreKtx)

    /**Navigation*/
    api(Navigation.navigationFragmentKtx)
    api(Navigation.navigationUiKtx)
    api(Navigation.navigationDFM)

    /**Hilt*/
    implementation(Hilt.hiltAndroid)
    implementation(Hilt.hiltViewModel)
    kapt(Hilt.hiltAndroidCompiler)
    kapt(Hilt.hiltCompiler)

    /**Retrofit*/
    Retrofit.components.forEach { implementation(it) }

    /**Test*/
    Test.components.forEach { testImplementation(it) }

    /**Android Test*/
    AndroidTest.components.forEach { androidTestImplementation(it) }
}