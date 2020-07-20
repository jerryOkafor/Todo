package me.jerryhanks.todo.timeline

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.jerryhanks.todo.core.di.TodoService
import timber.log.Timber

class TimelineViewModel @ViewModelInject constructor(private val todoService: TodoService) :
    ViewModel() {

    fun getTodo() {
        viewModelScope.launch {
            val todo = todoService.todos()
            Timber.d("$todo")
        }
    }
}