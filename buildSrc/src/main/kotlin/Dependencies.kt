/**Default and Adroid Config*/
object Config {
    object Version {
        const val minSdkVersion = 21
        const val compileSdkVersion = 29
        const val targetSdkVersion = 29
        const val versionName = "1.0"
        const val versionCode = 1
    }

    const val isMultiDexEnabled = true

    object Android {
        const val applicationId = "me.jerryhanks.todo"
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

/**Help handle group of libraries*/
interface Libraries {
    val components: List<String>
}

object Dependencies {
    object Versions {
        const val room = "2.2.5"
        const val okHttp = "4.8.1"
    }

    object PlayServices : Libraries {
        object Versions {
            const val playServicesAuth = "18.1.0"
            const val apiClient = "1.22.0"
            const val apiClientAndroid = "1.22.0"
            const val tasks = "v1-rev71-1.25.0"
        }

        const val playServicesAuth =
            "com.google.android.gms:play-services-auth:${Versions.playServicesAuth}"
        const val apiClient = "com.google.api-client:google-api-client:${Versions.apiClient}"
        const val apiClientAndroid =
            "com.google.api-client:google-api-client-android:${Versions.apiClientAndroid}"
        const val tasks = "com.google.apis:google-api-services-tasks:${Versions.tasks}"

        override val components = listOf(playServicesAuth, apiClient, apiClientAndroid, tasks)
    }

    object Desugar : Libraries {
        object Version {
            const val desugar = "1.0.9"
        }

        const val desugaring = "com.android.tools:desugar_jdk_libs:${Version.desugar}"

        override val components = listOf(desugaring)

    }

    object Kotlin {
        object Versions {
            const val kotlin = "1.3.72"
            const val coroutines = "1.3.9"
        }

        const val stdlib: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        const val coroutineCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutineAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val coroutineTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    }

    object AndroidX : Libraries {
        object Versions {
            const val coreKtx = "1.3.0"
            const val lifeCycle = "2.3.0-alpha03"
            const val appCompat = "1.1.0"
            const val coreTesting = "2.1.0"
        }

        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appComppat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val lifeCycleCommon =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
        const val viewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"

        const val roomRuntime = "androidx.room:room-runtime:${Dependencies.Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Dependencies.Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Dependencies.Versions.room}"

        const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"

        override val components = listOf(coreKtx, lifeCycleCommon, viewModel, appComppat)
    }

    object Hilt : Libraries {
        object Versions {
            const val hilt = "2.28-alpha"
            const val hiltVM = "1.0.0-alpha01"
        }

        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltAndroidTest = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltVM}"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltVM}"

        override val components = listOf(
            hiltAndroid, hiltAndroidCompiler, hiltViewModel,
            hiltCompiler
        )
    }


    object Network : Libraries {
        object Versions {
            const val retrofit = "2.9.0"
        }

        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

        override val components = listOf(retrofit, gsonConverter)
    }

    object Navigation : Libraries {
        object Version {
            const val navigation = "2.3.0"
        }

        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
        const val navigationDFM =
            "androidx.navigation:navigation-dynamic-features-fragment:${Version.navigation}"

        override val components =
            listOf(navigationFragmentKtx, navigationFragmentKtx, navigationDFM)
    }

    /**View*/
    object View : Libraries {
        private object Versions {
            const val material = "1.1.0"
            const val constraintLayout = "1.1.3"
            const val calendarView = "0.4.0"
        }

        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val calenderView = "com.github.kizitonwose:CalendarView:${Versions.calendarView}"

        override val components = listOf(material, constraintLayout, calenderView)
    }

    /**Utils*/
    object Utils : Libraries {
        private object Versions {
            const val timber = "4.7.1"
            const val playCore = "1.7.3"
            const val playCoreKtx = "1.7.0"
        }

        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val playCore = "com.google.android.play:core:${Versions.playCore}"
        const val playCoreKtx = "com.google.android.play:core-ktx:${Versions.playCoreKtx}"
        override val components = listOf(timber, playCore, playCoreKtx)
    }

    /**Modules*/
    object ProjectModule {
        const val app = ":app"
        const val core = ":core"
        const val report = ":dataReportFeature"
        const val createTask = ":createTaskFeature"
        const val timeline = ":timeline"
    }


    object Test : Libraries {
        object Versions {
            const val junit = "4.13"
        }

        const val junit = "junit:junit:${Versions.junit}"
        const val roomTesting = "androidx.room:room-testing:${Dependencies.Versions.room}"
        const val mockWebServer =
            "com.squareup.okhttp3:mockwebserver:${Dependencies.Versions.okHttp}"

        override val components = listOf(junit)
    }

    object AndroidTest : Libraries {
        object Versions {
            const val testExtension = "1.1.1"
            const val espresso = "3.2.0"
        }

        const val testExtension = "androidx.test.ext:junit:${Versions.testExtension}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

        override val components = listOf(testExtension, espresso)
    }
}