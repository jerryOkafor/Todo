/**
 * @author jerry on 04/08/2020
 * for Todo
 **/
@file:Suppress("IllegalIdentifier")

package me.jerryhanks.todo.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import me.jerryhanks.todo.core.TestUtils
import me.jerryhanks.todo.core.data.db.daos.TaskDao
import me.jerryhanks.todo.core.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
//@HiltAndroidTest
class TaskDaoTest : BaseDaoTest() {

    private lateinit var taskDao: TaskDao

//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    override fun createDB() {
        super.createDB()
        taskDao = tasksDB.taskDao()

    }

    @Test
    fun shouldVerifyTaskExists_whenTaskIsInserted() = runBlocking {
        val task = TestUtils.createTask()
        taskDao.insertTask(task)

        val byId = taskDao.getTask(TestUtils.TEST_TASK_ID).getOrAwaitValue()
        assert(byId.id == task.id)
    }

}