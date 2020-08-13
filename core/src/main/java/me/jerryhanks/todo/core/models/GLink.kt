package me.jerryhanks.todo.core.models

import me.jerryhanks.todo.core.data.db.entities.Link


/**
 * @author jerry on 13/08/2020
 * for Todo
 **/
data class GLink(
    val taskId: String,
    val type: String,
    val description: String,
    val link: String
) {
    private fun toLink() = Link(
        -1L, taskId = taskId, type = type, description = description, link = link
    )
}