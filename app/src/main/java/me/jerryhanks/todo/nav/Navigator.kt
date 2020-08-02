package me.jerryhanks.todo.nav

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import me.jerryhanks.todo.R
import timber.log.Timber

/**
 * @author jerry on 19/07/2020
 * for Todo
 **/

interface Navigable

class Navigator(private val navigable: Navigable, lifecycleOwner: LifecycleOwner) {
    private var navController: NavController? = null

    private val navLifecycleObserver = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun bind() {
            Timber.d("I am here1")
            navController = when (navigable) {
                is AppCompatActivity -> navigable.findNavController(R.id.nav_host_fragment)
                is Fragment -> navigable.findNavController()
                else -> null
            }

            Timber.d("$navController")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun unbind() {
            Timber.d("I am here2")
            navController = null
        }
    }

    init {
        lifecycleOwner.lifecycle.addObserver(navLifecycleObserver)
    }

    fun navigate() {
        Timber.d("$navController")
    }
}