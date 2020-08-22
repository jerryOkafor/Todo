package me.jerryhanks.todo.createtaskfeature.ui.newTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.halfbit.knot3.CompositeKnot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.jerryhanks.todo.core.data.db.daos.TasksListDao
import me.jerryhanks.todo.core.data.db.entities.TasksList
import me.jerryhanks.todo.core.data.repository.TasksListRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * @author jerry on 19/07/2020
 * for Todo
 **/

sealed class State {
    object Empty : State()
    object Loading : State()
    data class Content(val books: List<TasksList>) : State()
    data class Error(val message: String) : State()
}

sealed class Event {
    object LoadTags : Event()
}

interface Delegate {
    fun CompositeKnot<State>.register(viewModelScope: CoroutineScope)
    fun CompositeKnot<State>.onEvent(event: Event): Boolean = false
}

interface KnotAction {
    fun perform(): LoadTagsAction.Result
}

interface LoadTagsAction : KnotAction {
    sealed class Result {
        data class Success(val books: List<TasksList>) : Result()
        sealed class Failure : Result() {
            object Network : Failure()
            object Generic : Failure()
        }
    }
}

class DefaultLoadTagsAction(
    private val viewModelScope: CoroutineScope,
    private val tasksListDao: TasksListDao
) : LoadTagsAction {
    override fun perform(): LoadTagsAction.Result {
        return LoadTagsAction.Result.Success(emptyList())
    }
}

class LoadTagsDelegate() : Delegate {
    override fun CompositeKnot<State>.register(viewModelScope: CoroutineScope) {

    }

}

class NewTaskViewModel @Inject constructor(
    private val tasksListRepository: TasksListRepository
) : ViewModel() {

    fun getTodos() {
        viewModelScope.launch {
            val taskLists = tasksListRepository.tasksList().collect {
                it.forEach { Timber.d("TaskList: $it") }
            }
        }
    }
}