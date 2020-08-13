package me.jerryhanks.todo.core.models

import me.jerryhanks.todo.core.data.db.entities.Task
import java.time.LocalDateTime


/**
 * @author jerry on 13/08/2020
 * for Todo
 **/

data class GTask(
    val id: String,
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
    val hidden: Boolean,
    val links: List<GLink>
) {
    private fun toTask() = Task(
        -1L,
        gId = id,
        eTag = eTag,
        title = title,
        updatedAt = updatedAt,
        selfLink = selfLink,
        parent = parent,
        position = position,
        notes = notes,
        status = status,
        due = due,
        completed = completed,
        deleted = deleted,
        hidden = hidden
    )
}