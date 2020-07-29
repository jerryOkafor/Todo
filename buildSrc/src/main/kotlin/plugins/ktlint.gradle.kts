package plugins

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("org.jlleitschuh.gradle.ktlint")
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs +=
            "-Xuse-experimental=" +
                    "kotlin.Experimental," +
                    "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                    "kotlinx.coroutines.InternalCoroutinesApi," +
                    "kotlinx.coroutines.FlowPreview"
    }


    ktlint {
        version.set("0.37.2")
        debug.set(true)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(false)
        outputColorName.set("RED")
        ignoreFailures.set(true)
        enableExperimentalRules.set(true)
        additionalEditorconfigFile.set(file("$rootDir/config/ktlint/.editorconfig"))
        disabledRules.set(setOf("insert_final_newline", "no-wildcard-imports", "import-ordering"))
//        reporters {
//            reporter(ReporterType.PLAIN)
//            reporter(ReporterType.CHECKSTYLE)
//
//            customReporters {
//                register("csv") {
//                    fileExtension = "csv"
//                    dependency = project(":project-reporters:csv-reporter")
//                }
//                register("yaml") {
//                    fileExtension = "yml"
//                    dependency = "com.example:ktlint-yaml-reporter:1.0.0"
//                }
//            }
//        }
//        kotlinScriptAdditionalPaths {
//            include(fileTree("scripts/"))
//        }
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}
