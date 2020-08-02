package me.jerryhanks.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import me.jerryhanks.core.di.TodoService

/**
 * @author jerry on 19/07/2020
 * for Todo
 **/

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface CreateTaskModuleDependencies {
    fun todoService(): TodoService
}