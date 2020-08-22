/**
 * @author jerry on 19/07/2020
 * for Todo
 **/
package me.jerryhanks.todo.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import me.jerryhanks.todo.core.data.TasksListDataSource
import me.jerryhanks.todo.core.data.api.TaskService
import me.jerryhanks.todo.core.data.db.daos.TasksListDao

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface CreateTaskModuleDependencies {
    fun todoService(): TaskService
    fun tasksListDataSource(): TasksListDataSource
    fun tasksListDao(): TasksListDao
//    fun tasksViewModelProviderFactory(): ViewModelProvider.Factory
}