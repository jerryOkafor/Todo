/**
 * @author jerry on 22/08/2020
 * for Todo
 **/
@file:Suppress("IllegalIdentifier")

package me.jerryhanks.todo.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import me.jerryhanks.todo.core.data.db.daos.TasksListDao
import me.jerryhanks.todo.core.utils.DefaultValues
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
//@HiltAndroidTest
class TaskListDaoTest : BaseDaoTest() {
    private lateinit var tasksLisDao: TasksListDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    override fun createDB() {
        super.createDB()
        tasksLisDao = tasksDB.taskListDao()

    }

    @Test
    fun shouldReturnListofTasksLists_whenDbisCreated() = runBlockingTest {
        val testValues = DefaultValues.defaultTasksLists()
        val job = launch {
            val tasksLists = tasksLisDao.tasksLists()
            assertEquals(tasksLists.toList(), testValues)
        }

        job.cancel()
    }


}