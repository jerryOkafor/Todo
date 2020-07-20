package me.jerryhanks.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.FragmentScoped
import me.jerryhanks.todo.core.di.TodoService
import javax.inject.Singleton


/**
 * @author jerry on 19/07/2020
 * for Todo
 **/

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface CreateTaskModuleDependencies {
    fun todoService(): TodoService
}

