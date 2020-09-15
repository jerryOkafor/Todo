import Dependencies.AndroidTest
import Dependencies.AndroidX
import Dependencies.Desugar
import Dependencies.Hilt
import Dependencies.Kotlin
import Dependencies.Navigation
import Dependencies.Network
import Dependencies.PlayServices
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
//https://www.danstone.uk/2020/06/dependency-injection-hilt-room-database.html
//https://medium.com/better-programming/hilt-a-new-dependency-injection-library-for-android-e6e00e719aeb

plugins {
    plugins.`common-android`
    applyKotlinKapt
    applyDaggerHilt
    plugins.`jacoco-report`
    id("com.google.gms.google-services")
}


android {
    useLibrary("android.test.runner")
    useLibrary("android.test.base")
    useLibrary("android.test.mock")

    dynamicFeatures = mutableSetOf(":createTaskFeature", ":dataReportFeature")

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    coreLibraryDesugaring(Desugar.desugaring)
    implementation(Kotlin.stdlib)
    implementation(Kotlin.coroutineCore)
    implementation(Kotlin.coroutineAndroid)

    /**Modules*/
    api(project(core))
    implementation(project(timeline))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifeCycleCommon)
    implementation(AndroidX.liveData)
    implementation(AndroidX.viewModel)

    implementation(View.material)
    implementation(View.constraintLayout)
    implementation(View.calenderView)

    /**Play Services*/
    implementation(PlayServices.playServicesAuth)
//    implementation(PlayServices.apiClient)
//    implementation(PlayServices.apiClientAndroid)
//    implementation(PlayServices.tasks)
    implementation("net.openid:appauth:0.2.0")

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
    testImplementation(Test.junit)
    testImplementation(Kotlin.coroutineTest)

    /**Android Test*/
    AndroidTest.components.forEach { androidTestImplementation(it) }
    androidTestImplementation("androidx.test:core:1.3.0")
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestUtil("androidx.test:orchestrator:1.3.0")
}