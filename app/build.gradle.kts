import Dependencies.AndroidTest
import Dependencies.AndroidX
import Dependencies.Desugar
import Dependencies.Hilt
import Dependencies.Kotlin
import Dependencies.Navigation
import Dependencies.Network
import Dependencies.ProjectModule.core
import Dependencies.ProjectModule.timeline
import Dependencies.Test
import Dependencies.Utils
import Dependencies.View

//https://developer.android.com/jetpack/androidx/migrate/artifact-mappings
//https://stackoverflow.com/questions/62712430/import-kotlinx-android-synthetic-failed-android-studio-doesnt-find-it-but-i-c
//https://issuetracker.google.com/issues/145888144
//https://developer.android.com/studio/write/java8-support
//https://pspdfkit.com/blog/2018/moving-your-gradle-build-scripts-to-kotlin/
//https://proandroiddev.com/sharing-build-logic-with-kotlin-dsl-203274f73013
//https://github.community/t/how-do-i-specify-job-dependency-running-in-another-workflow/16482
//https://medium.com/@ranjeetsinha/jacoco-with-kotlin-dsl-f1f067e42cd0
//https://developer.android.com/studio/test/command-line
//https://github.com/dalvin/AndroidTestsWithDagger2

plugins {
    plugins.`common-android`
    applyKotlinKapt
    applyDaggerHilt
    plugins.`jacoco-report`
}


android {
    useLibrary("android.test.runner")
    useLibrary("android.test.base")
    useLibrary("android.test.mock")

    dynamicFeatures = mutableSetOf(":createTaskFeature", ":dataReportFeature")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    coreLibraryDesugaring(Desugar.desugaring)
    implementation(Kotlin.stdlib)

    /**Modules*/
    implementation(project(core))
    implementation(project(timeline))


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
    Network.components.forEach { implementation(it) }

    /**Test*/
    Test.components.forEach { testImplementation(it) }

    /**Android Test*/
    AndroidTest.components.forEach { androidTestImplementation(it) }
    androidTestImplementation("androidx.test:core:1.2.0")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestUtil("androidx.test:orchestrator:1.2.0")
}