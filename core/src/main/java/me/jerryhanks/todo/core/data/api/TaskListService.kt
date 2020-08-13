package me.jerryhanks.todo.core.data.api

import me.jerryhanks.todo.core.models.GTaskList
import me.jerryhanks.todo.core.models.Resources
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @author jerry on 12/08/2020
 * for Todo
 **/

interface TaskListService {
    /**
     * Returns all authenticated user's task list.
     * */
    @GET("users/@me/lists")
    suspend fun lists(): Resources<GTaskList>

    /**
     * Returns the authenticated user's specified task list.
     * */
    @GET("users/@me/lists/{taskList}")
    suspend fun taskList(@Path("taskList") taskList: String): GTaskList

    //Todo : Add insert, update delete
}