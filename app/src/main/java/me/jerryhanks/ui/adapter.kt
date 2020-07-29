package me.jerryhanks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import me.jerryhanks.R
import me.jerryhanks.todo.core.data.db.Todo

/**
 * @author jerry on 25/07/2020
 * for Todo
 **/

class TodoAdapter : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        // do nothing
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class TodoViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

//        fun bind(todo: Todo) {
//
//        }
    }
}