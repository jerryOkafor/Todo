import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**Apply Maven to a repository*/
fun RepositoryHandler.maven(url: String) {
    maven {
        setUrl(url)
    }
}

fun RepositoryHandler.applyDefault() {
    google()
    jcenter()
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://plugins.gradle.org/m2/")
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

/**Plugin Extensions*/
//Apply Android
val PluginDependenciesSpec.applyAndroidApplication: PluginDependencySpec
    get() = id("com.android.application")

//Apply kotlin
val PluginDependenciesSpec.applyKotlinAndroid: PluginDependencySpec
    get() = kotlin("android")

//apply kotlin android extension
val PluginDependenciesSpec.applyKotlinAndroidExtension: PluginDependencySpec
    get() = kotlin("android.extensions")

//Apply Library
val PluginDependenciesSpec.applyLibrary: PluginDependencySpec
    get() = id("com.android.library")

//Apply Kotlin Library
val PluginDependenciesSpec.applyKotlinLibrary: PluginDependencySpec
    get() = kotlin("library")

//Apply dynamic feature
val PluginDependenciesSpec.applyDynamicFeature: PluginDependencySpec
    get() = id("com.android.dynamic-feature")


//Apply dagger hilt
val PluginDependenciesSpec.applyDaggerHilt: PluginDependencySpec
    get() = id("dagger.hilt.android.plugin")

//Apply nav safe args
val PluginDependenciesSpec.applyNavSafeArgs: PluginDependencySpec
    get() = id("androidx.navigation.safeargs.kotlin")

//Apply kotlin kapt plugin
val PluginDependenciesSpec.applyKotlinKapt: PluginDependencySpec
    get() = kotlin("kapt")

