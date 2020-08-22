/**
 * @author jerry on 12/08/2020
 * for Todo
 **/
package me.jerryhanks.todo.core.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import me.jerryhanks.todo.core.data.TasksListDataSource
import me.jerryhanks.todo.core.data.db.TasksDB
import me.jerryhanks.todo.core.data.repository.TasksListRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideTasksDb(@ApplicationContext context: Context) = TasksDB.getInstance(context, false)

    @Provides
    @Singleton
    fun provideTasksDao(db: TasksDB) = db.taskDao()

    @Provides
    @Singleton
    fun provideTasksListDao(db: TasksDB) = db.taskListDao()

}