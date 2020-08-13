package me.jerryhanks.todo.core.models

import me.jerryhanks.todo.core.data.db.entities.TasksList
import java.time.LocalDateTime


/**
 * @author jerry on 13/08/2020
 * for Todo
 **/

data class GTaskList(
    val id: String,
    val etag: String,
    val title: String,
    val updated: LocalDateTime,
    val selfLink: String
) {
    private fun toTaskList(): TasksList {
        return TasksList(
            -1L,
            gId = id,
            etag = etag,
            title = title,
            updated = updated,
            selfLink = selfLink
        )
    }
}