package me.jerryhanks.todo.core.data.api

import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import me.jerryhanks.todo.core.RestServiceTestHelper
import me.jerryhanks.todo.core.utils.LocalDateTimeConverter
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime


/**
 * @author jerry on 13/08/2020
 * for Todo
 **/


class TaskListServiceTest {
    private val dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/users/@me/lists" -> {
                    MockResponse()
                        .setBody(RestServiceTestHelper.getStringFromFile("mock/tasklist.json"))
                        .setResponseCode(200)
                }
                else -> MockResponse().setResponseCode(404)
            }
        }

    }
    private val mockWebServer = MockWebServer()
    private lateinit var taskListService: TaskListService

    @Before
    fun setUp() {
        mockWebServer.dispatcher = dispatcher
        mockWebServer.start()

        val gson =
            GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeConverter())
                .create()

        taskListService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(TaskListService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun listTaskLists() = runBlocking {
        val taskList = taskListService.lists()
        assert(taskList.items.isNotEmpty())
    }

}