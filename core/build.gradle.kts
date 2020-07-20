import Dependencies.AndroidTest
import Dependencies.AndroidX
import Dependencies.Hilt
import Dependencies.Kotlin
import Dependencies.Navigation
import Dependencies.Retrofit
import Dependencies.Test
import Dependencies.Utils
import Dependencies.View

plugins {
    applyLibrary
    applyKotlinAndroid
    applyKotlinAndroidExtension
    applyKotlinKapt
    applyDaggerHilt
}

android {
    compileSdkVersion(Config.Version.compileSdkVersion)
    defaultConfig {
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Kotlin.stdlib)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifeCycleCommon)
    implementation(AndroidX.liveData)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.roomRuntime)
    implementation(AndroidX.roomKtx)
    api(AndroidX.roomCompiler)

    implementation(View.material)
    implementation(View.constraintLayout)

    /**Utils*/
    api(Utils.timber)

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