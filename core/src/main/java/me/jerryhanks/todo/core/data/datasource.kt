package me.jerryhanks.todo.core.data

import kotlinx.coroutines.flow.Flow
import me.jerryhanks.todo.core.data.db.entities.TasksList

/**
 * @author jerry on 17/07/2020
 * for Todo
 **/

interface CommonDataSource {
    fun getMessage(): String
}

class Repository : CommonDataSource {
    override fun getMessage(): String {
        return "Hello Word, I am here oh!"
    }
}


interface TasksListDataSource {
    suspend fun tasksList(): Flow<List<TasksList>>
}