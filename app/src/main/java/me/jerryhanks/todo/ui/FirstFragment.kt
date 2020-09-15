/**
 * @author jerry on 26/07/2020
 * for Todo
 **/
package me.jerryhanks.todo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
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
import kotlinx.android.synthetic.main.fragment_first.*
import me.jerryhanks.todo.R
import me.jerryhanks.todo.core.data.db.entities.Task
import me.jerryhanks.todo.nav.Navigable
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
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

    private val todoAdapter by lazy { TodoAdapter() }
    private val daysOfWeek = daysOfWeekFromLocale()
    private var isAppBarIdle = true
    private var appBarOffset = 0
    private var isAppBarClosed = true
    private var expandedFirst = 0
    private var appBarMaxOffset = 0
    private var isExpanded = false

    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")

    private val abbBarOffsetChangeListener =
        AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            appBarOffset = verticalOffset
            val totalScrollRange = appBarLayout.totalScrollRange
//            val progress =
//                (-verticalOffset).toFloat() / totalScrollRange.toFloat()

            //mArrowImageView.setRotation(-progress * 180)

            isExpanded = verticalOffset == 0
            isAppBarIdle = appBarOffset >= 0 || appBarOffset <= appBarMaxOffset
            val alpha = (-verticalOffset).toFloat() / totalScrollRange

            smallLayout.alpha = alpha
            largeLayout.alpha = 1 - alpha

            // If the small layout is not visible, make it officially invisible so
            // it can't receive clicks.

            // If the small layout is not visible, make it officially invisible so
            // it can't receive clicks.
            if (alpha == 0f) {
                smallLayout.visibility = View.INVISIBLE
                largeLayout.visibility = View.VISIBLE
            } else if (smallLayout.visibility == View.INVISIBLE) {
                smallLayout.visibility = View.VISIBLE
                largeLayout.visibility = View.INVISIBLE
            }

            if (alpha == 1f) {
                largeLayout.visibility = View.INVISIBLE
            } else if (largeLayout.visibility == View.INVISIBLE) {
                largeLayout.visibility = View.VISIBLE
            }
        }

    private fun setExpandAndCollapseEnabled(enabled: Boolean) {
        if (todoRecycler.isNestedScrollingEnabled != enabled) {
            ViewCompat.setNestedScrollingEnabled(todoRecycler, enabled)
        }
    }

    private val aappBarTracking = object : TodoRecyclerView.AppBarTracking {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todoAdapter.submitList((0..30).map {
            Task(
                id = it.toLong(),
                gId = "gid",
                eTag = "",
                title = "Title",
                updatedAt = LocalDateTime.now(),
                selfLink = "Link",
                parent = "Parent",
                position = "000222",
                notes = "Note",
                status = "Status",
                due = LocalDateTime.parse("2020-08-09T06:30:00"),
                completed = LocalDateTime.parse("2020-08-30T06:30:00"),
                deleted = false,
                hidden = false
            )
        })
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
            appBarMaxOffset = -appBarLayout.totalScrollRange

            val layoutParam = (appBarLayout.layoutParams as CoordinatorLayout.LayoutParams)
            (layoutParam.behavior as? TodoAppBarBehavior)?.setCanOpenBottom(toolbar.height)
        }

        appBarLayout.addOnOffsetChangedListener(abbBarOffsetChangeListener)
        setExpandAndCollapseEnabled(false)

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
        todoRecycler.setAppBarTracking(aappBarTracking)
        todoRecycler.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        todoRecycler.adapter = todoAdapter
    }
}

//https://www.googleapis.com/auth/tasks