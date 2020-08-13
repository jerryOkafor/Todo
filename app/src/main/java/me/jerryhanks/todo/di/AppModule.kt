package me.jerryhanks.todo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import me.jerryhanks.todo.ui.Analytics
import me.jerryhanks.todo.ui.AnalyticsAdapter


/**
 * @author jerry on 13/08/2020
 * for Todo
 **/

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindAnalyticsService(adapter: AnalyticsAdapter): Analytics
}