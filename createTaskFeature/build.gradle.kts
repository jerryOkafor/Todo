import Dependencies.AndroidTest
import Dependencies.AndroidX
import Dependencies.Hilt
import Dependencies.Kotlin
import Dependencies.Navigation
import Dependencies.ProjectModule.app
import Dependencies.ProjectModule.core
import Dependencies.Network
import Dependencies.Test
import Dependencies.View

plugins {
    plugins.`common-dfm`
    applyKotlinKapt
    applyDaggerHilt
    plugins.`jacoco-report`
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
    Network.components.forEach { implementation(it) }

    /**Test*/
    Test.components.forEach { testImplementation(it) }

    /**Android Test*/
    AndroidTest.components.forEach { androidTestImplementation(it) }

//    androidTestImplementation 'androidx.annotation:annotation:1.1.0'
}