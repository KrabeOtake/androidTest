package com.abnamro.apps.referenceandroid

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.abnamro.apps.referenceandroid.utils.waitView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkMainText() {
        onView(isRoot()).perform(waitView(withText("Hello World!")))
        onView(withText("Hello World!")).check(matches(isDisplayed()))
    }

    @Test
    fun checkFabTap() {
        onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click())
        onView(isRoot()).perform(waitView(withText("Replace with your own action")))
    }

    @Test
    fun checkToolbarTitle() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.toolbar))))
    }

    @Test
    fun checkToolbarOptions() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        onView(withText(R.string.action_settings)).check(matches(isDisplayed()))
    }

    @Test
    fun checkToolbarSelectOption() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        onView(withText(R.string.action_settings)).perform(click())
        onView(withText(R.string.action_settings)).check(doesNotExist())
    }
}
