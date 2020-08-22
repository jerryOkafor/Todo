package me.jerryhanks.todo.core.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ViewModelFactoryModule {
    /**
     * Provides the MintViewModelFactory
     * */
    @Binds
    abstract fun provideViewModelFactory(factory: TasksViewModelProviderFactory)
            : ViewModelProvider.Factory
}