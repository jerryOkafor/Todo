/**
 * @author jerry on 22/08/2020
 * for Todo
 **/
package me.jerryhanks.todo.core.utils

import android.content.ContentValues
import java.time.LocalDateTime
import java.util.*

object DefaultValues {

    /**
     * Returns list of default TaskLists/Category for
     * pre-populating the Room Database at creation.
     * */
    @Suppress("MagicNumber", "StringLiteralDuplication", "LongMethod")
    fun defaultTasksLists(): List<ContentValues> {
        return listOf(
            ContentValues().apply {
                put("id", 1)
                put("gId", UUID.randomUUID().toString())
                put("title", "Study")
                put("etag", "")
                put("updated", LocalDateTime.now().toString())
                put("selfLink", "")
            },
            ContentValues().apply {
                put("id", 2)
                put("gId", UUID.randomUUID().toString())
                put("title", "Productivity")
                put("etag", "")
                put("updated", LocalDateTime.now().toString())
                put("selfLink", "")
            },
            ContentValues().apply {
                put("id", 3)
                put("gId", UUID.randomUUID().toString())
                put("title", "Life")
                put("etag", "")
                put("updated", LocalDateTime.now().toString())
                put("selfLink", "")
            },
            ContentValues().apply {
                put("id", 4)
                put("gId", UUID.randomUUID().toString())
                put("title", "Design")
                put("etag", "")
                put("updated", LocalDateTime.now().toString())
                put("selfLink", "")
            },
            ContentValues().apply {
                put("id", 5)
                put("gId", UUID.randomUUID().toString())
                put("title", "Business")
                put("etag", "")
                put("updated", LocalDateTime.now().toString())
                put("selfLink", "")
            },
            ContentValues().apply {
                put("id", 6)
                put("gId", UUID.randomUUID().toString())
                put("title", "Family")
                put("etag", "")
                put("updated", LocalDateTime.now().toString())
                put("selfLink", "")
            },
            ContentValues().apply {
                put("id", 7)
                put("gId", UUID.randomUUID().toString())
                put("title", "Work")
                put("etag", "")
                put("updated", LocalDateTime.now().toString())
                put("selfLink", "")
            }
        )
    }
}