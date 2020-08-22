/**
 * @author jerry on 22/08/2020
 * for Todo
 **/
package me.jerryhanks.todo.createtaskfeature.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoMap
import me.jerryhanks.todo.core.viewmodel.ViewModelKey
import me.jerryhanks.todo.createtaskfeature.ui.newTask.NewTaskViewModel

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class CreateTaskViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewTaskViewModel::class)
    abstract fun bindNewTaskViewModel(viewModel: NewTaskViewModel): ViewModel
}