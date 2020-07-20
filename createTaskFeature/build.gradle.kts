import Dependencies.AndroidTest
import Dependencies.AndroidX
import Dependencies.Hilt
import Dependencies.Kotlin
import Dependencies.Navigation
import Dependencies.ProjectModule.app
import Dependencies.ProjectModule.core
import Dependencies.Retrofit
import Dependencies.Test
import Dependencies.View

plugins {
    applyDynamicFeature
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

    @Suppress("UnstableApiUsage")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(app))
    implementation(project(core))
    implementation(Kotlin.stdlib)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifeCycleCommon)
    implementation(AndroidX.liveData)
    implementation(AndroidX.viewModel)

    implementation(View.material)
    implementation(View.constraintLayout)

    /**Utils*/
    api(Dependencies.Utils.timber)

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

//    androidTestImplementation 'androidx.annotation:annotation:1.1.0'
}