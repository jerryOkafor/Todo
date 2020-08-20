import Dependencies.AndroidTest
import Dependencies.AndroidX
import Dependencies.Desugar
import Dependencies.Hilt
import Dependencies.Kotlin
import Dependencies.Network
import Dependencies.Test
import Dependencies.View
import java.io.FileInputStream
import java.util.*

val propertyFileStream = FileInputStream("${rootDir}/app.properties")
val property = Properties().also { it.load(propertyFileStream) }
val googleTaskApiKey = property.getProperty("GOOGL_TASK_API_KEY")

plugins {
    plugins.`common-library`
    applyKotlinKapt
    applyDaggerHilt
    plugins.`jacoco-report`
}

android {
    defaultConfig {

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }
    buildTypes.all {
        buildConfigField("String", "GOOGLE_TASK_API_KEY", "\"${googleTaskApiKey}\"")
    }
    sourceSets {
        val androidTest by named("androidTest")
        val test by named("test")

        androidTest.apply {
            java.srcDirs("$projectDir/schemas", "src/sharedTest/java")
        }

        androidTest.apply {
            java.srcDirs("src/sharedTest/java")
        }
        test.apply {
            java.srcDirs("src/sharedTest/java")
        }
    }
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableTransformForLocalTests = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    coreLibraryDesugaring(Desugar.desugaring)
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
    testImplementation(Test.mockWebServer)
    testImplementation(Kotlin.coroutineTest)
    testImplementation(AndroidX.coreTesting)

    testImplementation(Hilt.hiltAndroidTest)
    kaptTest(Hilt.hiltAndroidCompiler)

    /**Android Test*/
    androidTestImplementation(AndroidTest.testExtension)
    androidTestImplementation(AndroidTest.espresso)
    androidTestImplementation(AndroidX.coreTesting)

    androidTestImplementation(Hilt.hiltAndroidTest)
    kaptAndroidTest(Hilt.hiltAndroidCompiler)

}