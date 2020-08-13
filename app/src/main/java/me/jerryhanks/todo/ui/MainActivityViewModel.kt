package me.jerryhanks.todo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.jerryhanks.todo.core.data.api.TaskService

/**
 * @author jerry on 19/07/2020
 * for Todo
 **/
class MainActivityViewModel @ViewModelInject constructor(private val todoService: TaskService) :
    ViewModel() {

    fun getTodos() {
        viewModelScope.launch {
//            val todos = todoService.todos()
//            Timber.d("$todos")
        }
    }
}