package me.jerryhanks.todo.createtaskfeature.di

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap
import me.jerryhanks.todo.createtaskfeature.ui.NewTaskActivity
import me.jerryhanks.todo.createtaskfeature.ui.NewTaskViewModel
import me.jerryhanks.todo.di.CreateTaskModuleDependencies
import me.jerryhanks.todo.core.viewmodel.ViewModelFactoryModule
import me.jerryhanks.todo.core.viewmodel.ViewModelKey
import javax.inject.Singleton

/**
 * @author jerry on 19/07/2020
 * for Todo
 **/

@Component(
    modules = [ViewModelFactoryModule::class, CreateTaskViewModelModule::class],
    dependencies = [CreateTaskModuleDependencies::class]
)
@Singleton
interface CreateTaskComponent {
    fun inject(activity: NewTaskActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(createTaskModuleDependencies: CreateTaskModuleDependencies): Builder
        fun build(): CreateTaskComponent
    }
}

@Module
@DisableInstallInCheck
abstract class CreateTaskViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewTaskViewModel::class)
    abstract fun bindNotificationViewModel(viewModel: NewTaskViewModel): ViewModel
}