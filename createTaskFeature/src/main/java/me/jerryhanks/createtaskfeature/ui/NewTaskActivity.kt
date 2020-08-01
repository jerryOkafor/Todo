package me.jerryhanks.createtaskfeature.ui

import android.os.Bundle
import android.transition.Slide
import android.view.Window
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.EntryPointAccessors
import me.jerryhanks.createtaskfeature.R
import me.jerryhanks.createtaskfeature.di.DaggerCreateTaskComponent
import me.jerryhanks.di.CreateTaskModuleDependencies
import me.jerryhanks.di.TodoService
import me.jerryhanks.todo.core.ui.BaseActivity
import javax.inject.Inject

class NewTaskActivity : BaseActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var todoService: TodoService

    private val newTaskViewModel by viewModels<NewTaskViewModel> { viewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Slide()
            exitTransition = Slide()
        }

        setContentView(R.layout.activity_new_task)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
//            setHomeAsUpIndicator(
//                ContextCompat.getDrawable(
//                    this@NewTaskActivity,
//                    R.drawable.ic_close
//                )
//            )
        }

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

        // invoke
        newTaskViewModel.getTodos()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }
}