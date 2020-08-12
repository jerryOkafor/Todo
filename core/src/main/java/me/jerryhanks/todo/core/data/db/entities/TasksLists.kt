package me.jerryhanks.todo.core.data.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.time.LocalDateTime


/**
 * @author jerry on 03/08/2020
 * for Todo
 **/

@Entity(tableName = "taskLists")
data class TasksList(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val gId: String,
    val etag: String,
    val title: String,
    val updated: LocalDateTime,
    val selfLink: String
)

data class TasksListWithTasks(
    @Embedded
    val taskList: TasksList,

    @Relation(parentColumn = "gId", entityColumn = "parent")
    val tasks: List<Task>
)