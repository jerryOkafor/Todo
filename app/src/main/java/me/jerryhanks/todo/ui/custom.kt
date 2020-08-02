package me.jerryhanks.todo.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

/**
 * @author jerry on 26/07/2020
 * for Todo
 **/

class TodoAppBarBehavior(context: Context, attrs: AttributeSet) :
    AppBarLayout.Behavior(context, attrs) {
    // Touch above this y-axis value can open the appBar.
    private var canOpenBottom = 0

    // Determines if the appBar can be dragged open or not via direct touch on the appBar.
    private var canDrag = true
//    private val isPositive = false

    init {
        setUp()
    }

    private fun setUp() {
        setDragCallback(object : DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return true
            }
        })
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        event: MotionEvent
    ): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && child.top <= -child.totalScrollRange) {
            canDrag = event.y < canOpenBottom
        }
        return super.onInterceptTouchEvent(parent, child, event)
    }

    fun setCanOpenBottom(bottom: Int) {
        canOpenBottom = bottom
    }

//    companion object {
//        private const val TOP_CHILD_FLING_THRESHOLD = 3
//    }
}

class TodoRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {
    private var appBarTracking: AppBarTracking? = null
    private var view: View? = null
//    private var current: Int = 0
    private var layoutManager: LinearLayoutManager? = null

    interface AppBarTracking {
        fun isAppBarIdle(): Boolean
        fun isAppBarExpanded(): Boolean
        fun isAppBarClosed(): Boolean
        fun appbaroffset(): Int
    }

//    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
//        if (ev.action != MotionEvent.ACTION_MOVE) {
//            if (appBarTracking!!.isAppBarExpanded()) {
//                current = layoutManager!!.findFirstVisibleItemPosition()
//            }
//        }
//        return super.dispatchTouchEvent(ev)
//    }

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        if (type == ViewCompat.TYPE_NON_TOUCH && appBarTracking!!.isAppBarIdle() &&
            isNestedScrollingEnabled
        ) {
            if (dy > 0) {
                if (appBarTracking!!.isAppBarExpanded()) {
                    consumed!![1] = dy
                    return true
                }
            } else {
                view = layoutManager!!.findViewByPosition(appBarTracking!!.appbaroffset())
                if (view != null) {
                    consumed!![1] = dy - view!!.top + MainActivity.topspace
                }

                return true
            }
        }
        if (dy < 0 && type == ViewCompat.TYPE_TOUCH && appBarTracking!!.isAppBarExpanded()) {
            consumed!![1] = dy
            return true
        }

        val returnValue = super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type)

        if (offsetInWindow != null && !isNestedScrollingEnabled && offsetInWindow[1] != 0) {
            offsetInWindow[1] = 0
        }
        return returnValue
    }

    override fun setLayoutManager(layout: LayoutManager?) {
        super.setLayoutManager(layout)
        layoutManager = layout as LinearLayoutManager
    }

    fun setAppBarTracking(tracking: AppBarTracking) {
        appBarTracking = tracking
    }

    override fun fling(velocityX: Int, velocityY: Int): Boolean {
        var vY = velocityY
        if (!appBarTracking!!.isAppBarIdle()) {
            val vc = ViewConfiguration.get(context)
            vY = if (vY < 0) -vc.scaledMinimumFlingVelocity
            else vc.scaledMinimumFlingVelocity
        }

        return super.fling(velocityX, vY)
    }
}