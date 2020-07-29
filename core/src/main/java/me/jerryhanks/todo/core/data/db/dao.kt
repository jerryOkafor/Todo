package me.jerryhanks.todo.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * @author jerry on 17/07/2020
 * for Todo
 **/

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateNote(todo: Todo): Long
}