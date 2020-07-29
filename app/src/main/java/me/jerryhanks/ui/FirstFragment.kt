package me.jerryhanks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.kizitonwose.calendarview.CalendarView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.calendar_day_legend.*
import kotlinx.android.synthetic.main.fragment_first.*
import me.jerryhanks.R
import me.jerryhanks.nav.Navigable
import me.jerryhanks.nav.Navigator
import me.jerryhanks.todo.core.data.db.Todo
import timber.log.Timber
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.*

fun daysOfWeekFromLocale(): Array<DayOfWeek> {
    val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
    var daysOfWeek = DayOfWeek.values()
    // Order `daysOfWeek` array so that firstDayOfWeek is at index 0.
    // Only necessary if firstDayOfWeek != DayOfWeek.MONDAY which has ordinal 0.
    if (firstDayOfWeek != DayOfWeek.MONDAY) {
        val rhs = daysOfWeek.sliceArray(firstDayOfWeek.ordinal..daysOfWeek.indices.last)
        val lhs = daysOfWeek.sliceArray(0 until firstDayOfWeek.ordinal)
        daysOfWeek = rhs + lhs
    }
    return daysOfWeek
}

class DayBinderView(view: View) : ViewContainer(view) {
    lateinit var day: CalendarDay
    val textView = view.findViewById<TextView>(R.id.calendarDayText)
}

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), Navigable {

    private lateinit var todoAdapter: TodoAdapter
    private val daysOfWeek = daysOfWeekFromLocale()
    private var isAppBarIdle = true
    private var appBarOffset = 0
    private var isAppBarClosed = true
    private var expandedFirst = 0
    private var appBarMaxOffset = 0
    private var isExpanded = false

    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")

    private val layoutManager by lazy {
        LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private val abbBarOffsetChangeListener =
        AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            Timber.d("Offset: $verticalOffset")
            if (appBarOffset != verticalOffset) {
                appBarOffset = verticalOffset
                appBarMaxOffset = -appBarLayout.totalScrollRange

                val totalScrollRange = appBarLayout.totalScrollRange
                val progress =
                    (-verticalOffset).toFloat() / totalScrollRange.toFloat()

                arrowImageView.rotation = progress * 180
                Timber.d("Progress: $progress")

                isExpanded = verticalOffset == 0
                isAppBarIdle = appBarOffset >= 0 || appBarOffset <= appBarMaxOffset

                if (appBarOffset == -appBarLayout.totalScrollRange) {
                    isAppBarClosed = true
                    toolbarViews.visibility = View.VISIBLE
                    setExpandAndCollapseEnabled(false)
                } else {
                    setExpandAndCollapseEnabled(true)
//                    toolbarViews.visibility = View.INVISIBLE
                }
                if (appBarOffset == 0) {

                    toolbarViews.visibility = View.INVISIBLE

                    if (isAppBarClosed) {
                        todoRecycler.stopScroll()
                        isAppBarClosed = false
                        expandedFirst = layoutManager.findFirstVisibleItemPosition()

                        MainActivity.topspace =
                            layoutManager.findViewByPosition(layoutManager.findFirstVisibleItemPosition())!!.top
                    }
                }
            }
        }

    private fun setExpandAndCollapseEnabled(enabled: Boolean) {
        if (todoRecycler.isNestedScrollingEnabled != enabled) {
            ViewCompat.setNestedScrollingEnabled(todoRecycler, enabled)
        }
    }

    private val abbBarTracking = object : TodoRecyclerView.AppBarTracking {
        override fun isAppBarIdle(): Boolean {
            return isAppBarIdle
        }

        override fun isAppBarExpanded(): Boolean {
            return appBarOffset == 0
        }

        override fun isAppBarClosed(): Boolean {
            return isAppBarClosed
        }

        override fun appbaroffset(): Int {
            return expandedFirst
        }
    }

//    private val navigator by lazy {
//        Navigator(this, this)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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

        setupCalendar(calendarView)
        setupCalendar(calendarView2)

        legendLayout.children.forEachIndexed { index, v ->
            (v as TextView).apply {
                text = daysOfWeek[index].getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                    .toUpperCase(Locale.ENGLISH)
//                setTextColorRes(R.color.example_1_white_light)
            }
        }

        appBarLayout.post {
        }

        appBarLayout.addOnOffsetChangedListener(abbBarOffsetChangeListener)

        arrowImageView.setOnClickListener {
            isExpanded = !isExpanded
            todoRecycler.stopScroll()

            appBarLayout.setExpanded(isExpanded, true)
        }

        arrowImageView2.setOnClickListener {
            isExpanded = !isExpanded
            todoRecycler.stopScroll()

            appBarLayout.setExpanded(isExpanded, true)
        }

        calendarView.monthScrollListener = { bindCalendar(it) }
        calendarView2.monthScrollListener = { bindCalendar(it) }
    }

    private fun bindCalendar(calendar: CalendarMonth) {
        monthname.text = monthTitleFormatter.format(calendar.yearMonth)
    }

    private fun setupCalendar(calendar: CalendarView) {
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(10)
        val endMonth = currentMonth.plusMonths(10)

        calendar.setup(startMonth, endMonth, daysOfWeek.first())
//        calendar.scrollToMonth(currentMonth)
        calendar.scrollToDate(LocalDate.now(), DayOwner.THIS_MONTH)

        calendar.dayBinder = object : DayBinder<DayBinderView> {

            override fun create(view: View) = DayBinderView(view)

            override fun bind(container: DayBinderView, day: CalendarDay) {
                container.day = day
                val textView = container.textView
                textView.text = day.date.dayOfMonth.toString()
            }
        }
    }

    private fun setupRecyclerView() {
        todoRecycler.setAppBarTracking(abbBarTracking)
        todoAdapter = TodoAdapter()
        todoRecycler.layoutManager = layoutManager
        todoRecycler.adapter = todoAdapter

        todoAdapter.submitList((0..30).map { Todo(id = it.toLong()) })
    }
}