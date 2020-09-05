package com.abnamro.apps.referenceandroid.utils

import android.view.View
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import org.hamcrest.Matcher
import java.util.concurrent.TimeoutException


fun waitView(matcher: Matcher<View>, timeout: Long = 3000): WaitAction {
    return WaitAction(timeout, matcher)
}

class WaitAction(private val timeout: Long,
                 private val matcher: Matcher<View>) : ViewAction {
    override fun getDescription() = "Wait for text for $timeout milliseconds"

    override fun getConstraints() = ViewMatchers.isAssignableFrom(View::class.java)

    override fun perform(uiController: UiController, view: View) {
        uiController.loopMainThreadUntilIdle()
        val startTime = System.currentTimeMillis()
        val endTime = startTime + timeout

        do {
            for (child in TreeIterables.breadthFirstViewTraversal(view)) {
                if (matcher.matches(child)) {
                    return
                }
            }

            uiController.loopMainThreadForAtLeast(50)
        } while (System.currentTimeMillis() < endTime)

        throw PerformException.Builder()
            .withActionDescription(this.description)
            .withViewDescription(HumanReadables.describe(view))
            .withCause(TimeoutException())
            .build()
    }
}