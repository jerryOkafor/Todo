package me.jerryhanks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.calendar_day_layout.*
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

        setupRecyclerView()

        class DayBinderView(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay
            val textView = calendarDayText
        }

        calendarView.dayBinder = object : DayBinder<DayBinderView> {

            override fun create(view: View) = DayBinderView(view)

            override fun bind(container: DayBinderView, day: CalendarDay) {
                container.day = day
                val textView = container.textView
                textView.text = day.date.dayOfMonth.toString()
            }

        }

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