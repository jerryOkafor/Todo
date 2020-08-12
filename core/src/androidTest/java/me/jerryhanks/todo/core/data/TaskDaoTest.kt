@file:Suppress("IllegalIdentifier")

package me.jerryhanks.todo.core.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import me.jerryhanks.todo.core.TestUtils
import me.jerryhanks.todo.core.data.db.TasksDB
import me.jerryhanks.todo.core.data.db.daos.TaskDao
import me.jerryhanks.todo.core.getOrAwaitValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


/**
 * @author jerry on 04/08/2020
 * for Todo
 **/

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class TaskDaoTest {
    private lateinit var tasksDB: TasksDB

    private lateinit var taskDao: TaskDao

//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        tasksDB = TasksDB.getInstance(context, true)
        taskDao = tasksDB.taskDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        tasksDB.close()
    }

    @Test
    fun insertAndGetTask() = runBlocking {
        val task = TestUtils.createTask()

        taskDao.insertTask(task)
        val byId = taskDao.getTask(TestUtils.TEST_TASK_ID).getOrAwaitValue()
        assert(byId.id == task.id)
    }

}