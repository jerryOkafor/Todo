package me.jerryhanks.todo.core.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject


/**
 * @author jerry on 19/07/2020
 * for Todo
 **/

data class Todo(val id: String, val userId: String, val title: String, val completed: Boolean)

interface TodoService {

    @GET("todos")
    suspend fun todos(): List<Todo>
}

interface Analytics {
    fun analyse(from: String, context: Context): String
}

class AnalyticsAdapter @Inject constructor() :
    Analytics {
    override fun analyse(from: String, context: Context): String {
        return "Hi there, I am analysing $from as you can see I am here : ${context.applicationContext.packageName}"
    }
}


@Module
@InstallIn(ActivityComponent::class)
abstract class AnalyticsModule {

    @Binds
    abstract fun bindAnalyticsService(adapter: AnalyticsAdapter): Analytics
}

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    //You can Gson and okHttp Client instance here
    @Provides
    fun provideTodoService(): TodoService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoService::class.java)
    }
}