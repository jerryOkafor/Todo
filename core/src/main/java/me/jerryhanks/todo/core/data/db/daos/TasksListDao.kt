package me.jerryhanks.todo.core.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.jerryhanks.todo.core.data.db.entities.TasksList


/**
 * @author jerry on 22/08/2020
 * for Todo
 **/

@Dao
abstract class TasksListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(tasksList: TasksList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(tasksLists: List<TasksList>)

    @Query("SELECT * FROM taskLists")
    abstract suspend fun tasksLists(): List<TasksList>
}