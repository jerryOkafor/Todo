/**
 * @author jerry on 22/08/2020
 * for Todo
 **/
package me.jerryhanks.todo.core.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import me.jerryhanks.todo.core.data.db.TasksDB
import org.junit.After
import org.junit.Before
import java.io.IOException

@Suppress("UnnecessaryAbstractClass")
abstract class BaseDaoTest {
    protected lateinit var tasksDB: TasksDB

    @Before
    open fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        tasksDB = TasksDB.getInstance(context, true)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        tasksDB.close()
    }
}