package me.todo.core.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
abstract class ViewModelFactoryModule {
    /**
     * Provides the MintViewModelFactory
     * */
    @Binds
    abstract fun provideViewModelFactory(factory: TodoViewModelFactory): ViewModelProvider.Factory
}