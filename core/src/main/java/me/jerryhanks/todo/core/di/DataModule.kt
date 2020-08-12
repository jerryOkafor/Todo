package me.jerryhanks.todo.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


/**
 * @author jerry on 12/08/2020
 * for Todo
 **/
@Module
@InstallIn(ActivityComponent::class)
abstract class AnalyticsModule {

    @Binds
    abstract fun bindAnalyticsService(adapter: AnalyticsAdapter): Analytics
}