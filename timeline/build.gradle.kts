import Dependencies.AndroidTest
import Dependencies.AndroidX
import Dependencies.Desugar
import Dependencies.Kotlin
import Dependencies.ProjectModule.core
import Dependencies.Test

//./gradlew clean connectedAndroidTest mergeAndroidReports --continue

plugins {
    plugins.`common-library`
    applyKotlinKapt
//    applyDaggerHilt
    plugins.`jacoco-report`
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    coreLibraryDesugaring(Desugar.desugaring)
    implementation(Kotlin.stdlib)

    /**Modules*/
    implementation(project(core))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appComppat)

    implementation(Test.junit)

    androidTestImplementation(AndroidTest.testExtension)
    androidTestImplementation(AndroidTest.espresso)

}