/**
 * @author jerry on 22/08/2020
 * for Todo
 **/
package me.jerryhanks.todo.createtaskfeature.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import me.jerryhanks.todo.createtaskfeature.ui.newTask.NewTaskFragment

@Module
@InstallIn(FragmentComponent::class)
abstract class CreateTaskModule {
    @ContributesAndroidInjector
    @FragmentScoped
    internal abstract fun contributeNewTasksFragment(): NewTaskFragment
}