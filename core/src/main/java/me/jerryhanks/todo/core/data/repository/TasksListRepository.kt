/**
 * @author jerry on 22/08/2020
 * for Todo
 **/
package me.jerryhanks.todo.core.data.repository

import kotlinx.coroutines.flow.Flow
import me.jerryhanks.todo.core.data.TasksListDataSource
import me.jerryhanks.todo.core.data.db.daos.TasksListDao
import me.jerryhanks.todo.core.data.db.entities.TasksList
import javax.inject.Inject


class TasksListRepository
@Inject constructor(private val tasksListDao: TasksListDao) : TasksListDataSource {
    override suspend fun tasksList(): Flow<List<TasksList>> {
        return tasksListDao.tasksLists()
    }
}
