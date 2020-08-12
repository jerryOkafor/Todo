package me.jerryhanks.todo.core.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


/**
 * @author jerry on 12/08/2020
 * for Todo
 **/


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    // You can Gson and okHttp Client instance here
//    @Provides
//    fun provideTodoService(): TodoService {
//        return Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(TodoService::class.java)
//    }
}