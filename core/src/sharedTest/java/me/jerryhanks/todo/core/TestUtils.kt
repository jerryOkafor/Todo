package me.jerryhanks.todo.core

import me.jerryhanks.todo.core.data.db.entities.Task
import me.jerryhanks.todo.core.data.db.entities.TasksList
import java.time.LocalDateTime
import java.util.*


/**
 * @author jerry on 05/08/2020
 * for Todo
 **/

object TestUtils {
    const val TEST_TASK_ID = 1L
    const val TASK_LIST_ID = 1L
    const val TEST_TASK_NOTE = "Noted goes here"
    const val TEST_TASK_STATUS = "status"
    const val TEST_TASK_TITLE = "Respond to Email Conversation"

    fun createTask(id: Long = TEST_TASK_ID): Task {
        return Task(
            id = id,
            gId = "gid",
            eTag = "",
            title = TEST_TASK_TITLE,
            updatedAt = LocalDateTime.parse("2020-08-03T06:30:00"),
            selfLink = "Link",
            parent = "Parent",
            position = "000222",
            notes = TEST_TASK_NOTE,
            status = TEST_TASK_STATUS,
            due = LocalDateTime.parse("2020-08-09T06:30:00"),
            completed = LocalDateTime.parse("2020-08-30T06:30:00"),
            deleted = false,
            hidden = false
        )
    }
}