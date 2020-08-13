package me.jerryhanks.todo.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import me.jerryhanks.todo.core.data.api.TaskListService
import me.jerryhanks.todo.core.data.api.TaskService
import me.jerryhanks.todo.core.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author jerry on 12/08/2020
 * for Todo
 **/


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    //You can Gson and okHttp Client instance here
    @Provides
    fun provideTasksListService(retrofit: Retrofit): TaskListService {
        return retrofit.create(TaskListService::class.java)
    }

    @Provides
    fun provideTaskService(retrofit: Retrofit): TaskService {
        return retrofit.create(TaskService::class.java)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttp(): OkHttpClient {
        val client = OkHttpClient.Builder()

        client.addInterceptor {
            val originalRequest = it.request()
            val originalUrl = originalRequest.url()

            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("key", Constants.API_KEY)
                .build()
            val newRequestBuilder = originalRequest.newBuilder()
                .header("Authorization", "Bearer TOKEN")
                .url(newUrl)

            val request = newRequestBuilder.build()
            return@addInterceptor it.proceed(request)
        }

        return client.build()
    }
}