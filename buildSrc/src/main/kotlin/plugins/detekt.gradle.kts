package plugins

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

val analysisDir = file(projectDir)
val baselineFile = file("$rootDir/config/detekt/baseline.xml")
val configFile = file("$rootDir/config/detekt/detekt.yml")
val formatConfigFile = file("$rootDir/config/detekt/format.yml")
val statisticsConfigFile = file("$rootDir/config/detekt/statistics.yml")

val kotlinFiles = "**/*.kt"
val kotlinScriptFiles = "**/*.kts"
val resourceFiles = "**/resources/**"
val buildFiles = "**/build/**"

plugins {
    id("io.gitlab.arturbosch.detekt")
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        buildUponDefaultConfig = true
        config = files(configFile)
        baseline = baselineFile

        reports {
            xml.enabled = true
            html.enabled = true
            txt.enabled = true
        }
    }

    tasks {
        withType<Detekt>().configureEach {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    val detektFormat by tasks.registering(Detekt::class) {
        description = "Formats whole project."
        parallel = true
        disableDefaultRuleSets = true
        buildUponDefaultConfig = true
        autoCorrect = true
        setSource(analysisDir)
        config.setFrom(listOf(statisticsConfigFile, formatConfigFile))
        include(kotlinFiles)
        include(kotlinScriptFiles)
        exclude(resourceFiles)
        exclude(buildFiles)
        baseline.set(baselineFile)
        reports {
            xml.enabled = false
            html.enabled = false
            txt.enabled = false
        }
    }

    val detektAll by tasks.registering(Detekt::class) {
        description = "Runs the whole project at once."
        parallel = true
        buildUponDefaultConfig = true
        setSource(analysisDir)
        config.setFrom(listOf(statisticsConfigFile, configFile))
        include(kotlinFiles)
        include(kotlinScriptFiles)
        exclude(resourceFiles)
        exclude(buildFiles)
        baseline.set(baselineFile)
        reports {
            xml.enabled = false
            html.enabled = true
            txt.enabled = false
        }
    }

    val detektProjectBaseline by tasks.registering(DetektCreateBaselineTask::class) {
        description = "Overrides current baseline."
        buildUponDefaultConfig.set(true)
        ignoreFailures.set(true)
        parallel.set(true)
        setSource(analysisDir)
        config.setFrom(listOf(statisticsConfigFile, configFile))
        include(kotlinFiles)
        include(kotlinScriptFiles)
        exclude(resourceFiles)
        exclude(buildFiles)
        baseline.set(baselineFile)
    }
}