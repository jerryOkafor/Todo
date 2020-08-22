/**
 * @author jerry on 19/07/2020
 * for Todo
 **/
package me.jerryhanks.todo.createtaskfeature.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.jerryhanks.todo.core.viewmodel.ViewModelFactoryModule
import me.jerryhanks.todo.createtaskfeature.ui.NewTaskActivity
import me.jerryhanks.todo.di.CreateTaskModuleDependencies
import javax.inject.Singleton

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