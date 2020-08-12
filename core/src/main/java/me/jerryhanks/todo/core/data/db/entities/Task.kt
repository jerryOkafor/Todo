package me.jerryhanks.todo.core.data.db.entities

import androidx.room.*
import java.time.LocalDateTime

/**
 * @author jerry on 17/07/2020
 * for Todo
 **/

@Entity(tableName = "links")
data class Link(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val taskId: String,
    val type: String,
    val description: String,
    val link: String
)

@Entity(tableName = "tasks", indices = [Index(value = ["id"], unique = true)])
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val gId: String,
    val eTag: String,
    val title: String,
    val updatedAt: LocalDateTime,
    val selfLink: String,
    val parent: String,
    val position: String,
    val notes: String,
    val status: String,
    val due: LocalDateTime,
    val completed: LocalDateTime,
    val deleted: Boolean,
    val hidden: Boolean
)

data class TaskWithLinks(
    @Embedded val task: Task,
    @Relation(parentColumn = "id", entityColumn = "taskId")
    val links: List<Link>
)