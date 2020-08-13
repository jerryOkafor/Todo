package me.jerryhanks.todo.createtaskfeature.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.jerryhanks.todo.core.data.api.TaskService
import javax.inject.Inject

/**
 * @author jerry on 19/07/2020
 * for Todo
 **/

class NewTaskViewModel @Inject constructor(private val todoService: TaskService) : ViewModel() {

    fun getTodos() {
        viewModelScope.launch {
//            val todos = todoService.todos()
//
//            Timber.d("$todos")
        }
    }
}