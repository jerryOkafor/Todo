package me.jerryhanks.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import me.jerryhanks.todo.core.viewmodel.TodoViewModelFactory

@Module
@DisableInstallInCheck
abstract class ViewModelFactoryModule {
    /**
     * Provides the MintViewModelFactory
     * */
    @Binds
    abstract fun provideViewModelFactory(factory: TodoViewModelFactory): ViewModelProvider.Factory
}