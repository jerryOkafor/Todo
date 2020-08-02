package me.todo.core.data

/**
 * @author jerry on 17/07/2020
 * for Todo
 **/

interface CommonDataSource {
    fun getMessage(): String
}

class Repository : CommonDataSource {
    override fun getMessage(): String {
        return "Hello Word, I am here oh!"
    }
}