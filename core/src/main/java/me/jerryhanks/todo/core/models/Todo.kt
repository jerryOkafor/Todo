package me.jerryhanks.todo.core.models


/**
 * @author jerry on 12/08/2020
 * for Todo
 **/
data class Todo(val id: String, val userId: String, val title: String, val completed: Boolean)