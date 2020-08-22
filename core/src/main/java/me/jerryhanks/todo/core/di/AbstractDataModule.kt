/**
 * @author jerry on 22/08/2020
 * for Todo
 **/
package me.jerryhanks.todo.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import me.jerryhanks.todo.core.data.TasksListDataSource
import me.jerryhanks.todo.core.data.repository.TasksListRepository

@Module
@InstallIn(ApplicationComponent::class)
abstract class AbstractDataModule {
    @Binds
    abstract fun provideTasksListRepo(tasksListRepository: TasksListRepository): TasksListDataSource
}
