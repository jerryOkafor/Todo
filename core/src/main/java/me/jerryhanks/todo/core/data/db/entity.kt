package me.jerryhanks.todo.core.data.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author jerry on 17/07/2020
 * for Todo
 **/
@Entity(tableName = "todo", indices = [Index(value = ["id"], unique = true)])
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Long
)