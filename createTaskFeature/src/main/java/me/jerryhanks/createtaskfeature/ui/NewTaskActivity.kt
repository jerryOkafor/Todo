package me.jerryhanks.createtaskfeature.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.EntryPointAccessors
import me.jerryhanks.createtaskfeature.R
import me.jerryhanks.createtaskfeature.di.DaggerCreateTaskComponent
import me.jerryhanks.di.CreateTaskModuleDependencies
import me.jerryhanks.todo.core.di.TodoService
import javax.inject.Inject

class NewTaskActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var todoService: TodoService

    private val newTaskViewModel by viewModels<NewTaskViewModel> { viewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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


        //invoke
        newTaskViewModel.getTodos()
    }


}