import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
//    plugins.common
    plugins.ktlint
//    plugins.detekt
}

buildscript {
    repositories.applyDefault()
}

allprojects {
    repositories.applyDefault()
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

subprojects {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs +=
            "-Xuse-experimental=" +
            "kotlin.Experimental," +
            "kotlinx.coroutines.ExperimentalCoroutinesApi," +
            "kotlinx.coroutines.InternalCoroutinesApi," +
            "kotlinx.coroutines.FlowPreview"
    }
}
