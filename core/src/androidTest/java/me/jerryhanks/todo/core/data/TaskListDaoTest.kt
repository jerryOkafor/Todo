/**
 * @author jerry on 22/08/2020
 * for Todo
 **/
@file:Suppress("IllegalIdentifier")

package me.jerryhanks.todo.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import me.jerryhanks.todo.core.TestUtils
import me.jerryhanks.todo.core.data.db.daos.TasksListDao
import me.jerryhanks.todo.core.utils.DefaultValues
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasSize
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
    fun shouldReturnListofTasksLists_whenDbisCreated() = runBlocking {
        val testValues = DefaultValues.defaultTasksListValues()
        val tasksLists = tasksLisDao.tasksLists()

        assertThat(tasksLists, hasSize(testValues.size))
        assertThat(tasksLists.first().id, `is`(TestUtils.TASK_LIST_ID))
    }


}