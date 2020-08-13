package me.jerryhanks.todo.core.data.api

import me.jerryhanks.todo.core.models.GTask
import me.jerryhanks.todo.core.models.Resources
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @author jerry on 12/08/2020
 * for Todo
 **/
interface TaskService {
    /**
     * Returns all tasks in the specified task list.
     * */
    @GET("/lists/{taskList}/tasks")
    suspend fun lists(@Path("taskList") tasksList: String): Resources<GTask>

    /**
     * Returns the specified task
     * */
    @GET("/lists/{taskList}/tasks/{task}")
    suspend fun task(@Path("task") task: String, @Path("tasksList") tasksList: String): GTask

    //Todo : add insert, update, delete clear and move
}