package me.jerryhanks.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.NestedScrollingChild2
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior

/**
 *
 * `SlidingPanelBehavior` provides all the functionality of
 * `AppBarLayout.ScrollingViewBehavior`
 * but allows a view that implements the `NestedScrollingChild2` interface, such as
 * `RecyclerView`, to slide up and down as a panel. This sliding panel effect is similar
 * to the functionality of the day/week/month/agenda view of the Google Calendar app when the
 * month toggle is clicked in the toolbar and a concise calendar month slides down from the top
 * and the day/week/month/agenda view slides down below it.
 *
 * <h2>Set Up</h2>
 * <h3>Layout Structure</h3>
 * Below is the general structure for the `CoordinatorLayout`. The key components here
 * are the scroll flags of the `CollapsingToolbarLayout`, the view that will toggle the
 * appbar from closed to expanded and the presence of the `NestedScrollingChild2` view which
 * is a `RecyclerView` here.
 *
 *
 * <pre>`<android.support.design.widget.CoordinatorLayout
 * <android.support.design.widget.AppBarLayout
 * <android.support.design.widget.CollapsingToolbarLayout
 * app:layout_scrollFlags="scroll|exitUntilCollapsed|enterAlways|snap"
 * ... >
 * <ImageView
 * android:layout_height="?dp"
 * android:layout_marginTop="?attr/actionBarSize"
 * ... />
 * <android.support.v7.widget.Toolbar
 * app:layout_collapseMode="pin"
 * ... >
 *
 * <!-- View goes here that toggles the sliding panel expanded/collapsed state -->
 *
 * <android.support.v7.widget.RecyclerView
 * app:layout_behavior=".SlidingPanelBehavior"
 * ... />
`</pre> *
 *
 *
 * <h3>Responsibilities of the App</h3>
 * The above layout will display most of the desired behavior. There are a few remaining
 * issues to be addressed by the app:
 * <br></br><br></br>
 *
 *  1.
 * Disabled dragging of the appbar open and closed via direct touch.
 * See [AppBarLayout.Behavior.DragCallback](https://developer.android.com/reference/android/support/design/widget/AppBarLayout.Behavior.DragCallback.html).
 * <br></br><br></br>
 *
 *  1.
 * Toggle the appbar expanded/collapsed. When toggled expanded, enable nested scrolling on the
 * `NestedScrollingChild2`. When toggled closed, disable nested scrolling on the
 * `NestedScrollingChild2`.
 *
 *
 */
class SlidingPanelBehavior(context: Context?, attrs: AttributeSet?) :
    ScrollingViewBehavior(context, attrs) {
    private var mAppBar: AppBarLayout? = null

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if (mAppBar == null && dependency is AppBarLayout) {
            // Capture our appbar for later use.
            mAppBar = dependency
        }
        return dependency is AppBarLayout
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: View,
        event: MotionEvent
    ): Boolean {
//        val action = event.action
        if (event.action != MotionEvent.ACTION_DOWN) { // Only want "down" events
            return false
        }
        if (getAppBarLayoutOffset(mAppBar) == -mAppBar!!.totalScrollRange) {
            // When appbar is collapsed, don't let it open through nested scrolling.
            setNestedScrollingEnabledWithTest(child as NestedScrollingChild2, false)
        } else {
            // Appbar is partially to fully expanded. Set nested scrolling enabled to activate
            // the methods within this behavior.
            setNestedScrollingEnabledWithTest(child as NestedScrollingChild2, true)
        }
        return false
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return (child as NestedScrollingChild2).isNestedScrollingEnabled
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        // How many pixels we must scroll to fully expand the appbar. This value is <= 0.
        val appBarOffset = getAppBarLayoutOffset(mAppBar)

        // Check to see if this scroll will expand the appbar 100% or collapse it fully.
        if (dy <= appBarOffset) {
            // Scroll by the amount that will fully expand the appbar and dispose of the rest (dy).
            super.onNestedPreScroll(
                coordinatorLayout, mAppBar!!, target, dx,
                appBarOffset, consumed, type
            )
            consumed[1] += dy
        } else if (dy >= mAppBar!!.totalScrollRange + appBarOffset) {
            // This scroll will collapse the appbar. Collapse it and dispose of the rest.
            super.onNestedPreScroll(
                coordinatorLayout, mAppBar!!, target, dx,
                mAppBar!!.totalScrollRange + appBarOffset,
                consumed, type
            )
            consumed[1] += dy
        } else {
            // This scroll will leave the appbar partially open. Just do normal stuff.
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        }
    }

    /**
     * `onNestedPreFling()` is overriden to address a nested scrolling defect that was
     * introduced in API 26. This method prevent the appbar from misbehaving when scrolled/flung.
     *
     *
     * Refer to ["Bug in design support library"](https://issuetracker.google.com/issues/65448468)
     */
    override fun onNestedPreFling(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if ((child as NestedScrollingChild2).isNestedScrollingEnabled) {
            // Just stop the nested fling and let the appbar settle into place.
            (child as NestedScrollingChild2).stopNestedScroll(ViewCompat.TYPE_NON_TOUCH)
            return true
        }
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
    }

    // Something goes amiss when the flag it set to its current value, so only call
    // setNestedScrollingEnabled() if it will result in a change.
    private fun setNestedScrollingEnabledWithTest(
        child: NestedScrollingChild2,
        enabled: Boolean
    ) {
        if (child.isNestedScrollingEnabled != enabled) {
            child.isNestedScrollingEnabled = enabled
        }
    }

    companion object {
        private fun getAppBarLayoutOffset(appBar: AppBarLayout?): Int {
            val behavior =
                (appBar!!.layoutParams as CoordinatorLayout.LayoutParams).behavior
            return if (behavior is AppBarLayout.Behavior) {
                behavior.topAndBottomOffset
            } else 0
        }
    }
}