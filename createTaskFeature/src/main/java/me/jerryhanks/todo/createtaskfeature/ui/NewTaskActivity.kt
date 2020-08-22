package me.jerryhanks.todo.createtaskfeature.ui

import android.content.Context
import android.os.Bundle
import android.transition.Slide
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.hilt.android.EntryPointAccessors
import me.jerryhanks.createtaskfeature.R
import me.jerryhanks.todo.core.di.Injectable
import me.jerryhanks.todo.core.ui.BaseActivity
import me.jerryhanks.todo.createtaskfeature.di.DaggerCreateTaskComponent
import me.jerryhanks.todo.di.CreateTaskModuleDependencies
import javax.inject.Inject

class NewTaskActivity : BaseActivity(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private val fragmentLifecyleCallback = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
            super.onFragmentAttached(fm, f, context)
            if (f is Injectable) {
                AndroidSupportInjection.inject(f)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCreateTaskComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    CreateTaskModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)

        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Slide()
            exitTransition = Slide()
        }

        setContentView(R.layout.activity_new_task)

        (this as? FragmentActivity)?.supportFragmentManager
            ?.registerFragmentLifecycleCallbacks(fragmentLifecyleCallback, true)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }


    override fun androidInjector(): DispatchingAndroidInjector<Any> {
        return androidInjector
    }

    override fun onDestroy() {
        super.onDestroy()
        (this as? FragmentActivity)?.supportFragmentManager
            ?.unregisterFragmentLifecycleCallbacks(fragmentLifecyleCallback)
    }
}