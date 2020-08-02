package me.jerryhanks.todo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.todo.core.di.TodoService
import timber.log.Timber

/**
 * @author jerry on 19/07/2020
 * for Todo
 **/
class MainActivityViewModel @ViewModelInject constructor(private val todoService: TodoService) :
    ViewModel() {

    fun getTodos() {
        viewModelScope.launch {
            val todos = todoService.todos()
            Timber.d("$todos")
        }
    }
}