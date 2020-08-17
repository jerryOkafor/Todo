import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    plugins.ktlint
    plugins.detekt
    id("android-reporting")
}


buildscript {
    val kotlinVersion by extra("1.3.72")
    repositories.applyDefault()
    dependencies {
        "classpath"("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
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


// optional:
//jacocoFull {
//    excludeProject(":excluded_module1", ":excluded_module2")
//}