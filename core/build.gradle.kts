import Dependencies.AndroidTest
import Dependencies.AndroidX
import Dependencies.Hilt
import Dependencies.Kotlin
import Dependencies.Network
import Dependencies.Test
import Dependencies.View

plugins {
    plugins.`common-library`
    applyKotlinKapt
//    applyDaggerHilt
    plugins.`jacoco-report`
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Kotlin.stdlib)
    implementation(Kotlin.coroutineCore)
    implementation(Kotlin.coroutineAndroid)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifeCycleCommon)
    implementation(AndroidX.liveData)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.appComppat)

    implementation(View.material)
    implementation(View.constraintLayout)

    /**Hilt*/
    implementation(Hilt.hiltAndroid)
    implementation(Hilt.hiltViewModel)
    kapt(Hilt.hiltAndroidCompiler)
    kapt(Hilt.hiltCompiler)

    /**Room*/
    implementation(AndroidX.roomRuntime)
    implementation(AndroidX.roomKtx)
    kapt(AndroidX.roomCompiler)

    /**Retrofit*/
    Network.components.forEach { implementation(it) }

    /**Tests*/
    testImplementation(Test.junit)
    testImplementation(Test.roomTesting)
    testImplementation(Kotlin.coroutineTest)

    /**Android Test*/
    androidTestImplementation(AndroidTest.testExtension)
    androidTestImplementation(AndroidTest.espresso)

}