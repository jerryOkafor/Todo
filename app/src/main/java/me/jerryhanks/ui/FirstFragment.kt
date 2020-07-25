package me.jerryhanks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_first.*
import me.jerryhanks.R
import me.jerryhanks.nav.Navigable
import me.jerryhanks.nav.Navigator
import me.jerryhanks.todo.core.data.db.Todo

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), Navigable {

    private lateinit var todoAdapter: TodoAdapter

    private val navigator by lazy {
        Navigator(this, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
//            supportActionBar?.title = "Home"
        }

//        collapsing_toolbar.title = "Welcome Home"

        setupRecyclerView()

//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter()
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        todoRecycler.layoutManager = layoutManager
        todoRecycler.adapter = todoAdapter

        val todos = (0..30).map { Todo(id = it.toLong()) }
        todoAdapter.submitList(todos)
    }
}