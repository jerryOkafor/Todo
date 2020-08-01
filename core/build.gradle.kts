import Dependencies.AndroidTest
import Dependencies.AndroidX
import Dependencies.Kotlin
import Dependencies.Test

plugins {
    plugins.`common-library`
    applyKotlinKapt
//    applyDaggerHilt
    plugins.`jacoco-report`
}


dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Kotlin.stdlib)


    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appComppat)

    implementation(Test.junit)

    androidTestImplementation(AndroidTest.testExtension)
    androidTestImplementation(AndroidTest.espresso)

}