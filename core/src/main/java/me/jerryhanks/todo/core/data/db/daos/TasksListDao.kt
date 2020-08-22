/**
 * @author jerry on 22/08/2020
 * for Todo
 **/
package me.jerryhanks.todo.core.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.jerryhanks.todo.core.data.db.entities.TasksList

@Dao
@Suppress("UnnecessaryAbstractClass")
abstract class TasksListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(tasksList: TasksList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(tasksLists: List<TasksList>)

    @Query("SELECT * FROM taskLists")
    abstract fun tasksLists(): Flow<List<TasksList>>

    @Query("SELECT * FROM taskLists WHERE gId=:gId")
    abstract fun taskList(gId: String): Flow<TasksList>
}