/**
 * @author jerry on 19/07/2020
 * for Todo
 **/
package me.jerryhanks.todo.createtaskfeature.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import me.jerryhanks.todo.core.viewmodel.ViewModelFactoryModule
import me.jerryhanks.todo.createtaskfeature.ui.NewTaskActivity
import me.jerryhanks.todo.createtaskfeature.ui.newTask.NewTaskFragment
import me.jerryhanks.todo.di.CreateTaskModuleDependencies
import javax.inject.Singleton

@Component(
    modules = [ViewModelFactoryModule::class, AndroidSupportInjectionModule::class,
        CreateTaskViewModelModule::class, CreateTaskModule::class],
    dependencies = [CreateTaskModuleDependencies::class]
)
@Singleton
interface CreateTaskComponent {
    fun inject(activity: NewTaskActivity)
//    fun inject(fragment: NewTaskFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(createTaskModuleDependencies: CreateTaskModuleDependencies): Builder
        fun build(): CreateTaskComponent
    }
}