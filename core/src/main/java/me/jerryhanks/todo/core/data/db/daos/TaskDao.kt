/**
 * @author jerry on 17/07/2020
 * for Todo
 **/
package me.jerryhanks.todo.core.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.jerryhanks.todo.core.data.db.entities.Task

@Dao
@Suppress("UnnecessaryAbstractClass")
abstract class TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTask(todo: Task)

    @Query("SELECT * FROM tasks WHERE :id = id")
    abstract fun getTask(id: Long): LiveData<Task>
}