package me.jerryhanks.todo.core.models


/**
 * @author jerry on 13/08/2020
 * for Todo
 **/
data class Resources<T>(val kind: String, val etag: String, val items: T)
