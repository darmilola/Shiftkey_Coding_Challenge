package com.shiftkey.codingchallenge

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import androidx.test.rule.ActivityTestRule
import org.junit.runner.RunWith
import com.shiftkey.codingchallenge.presentation.MainActivity

import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ShiftDisplayFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkRefreshIsDisplayed() {
        onView(withId(R.id.shift_item_refresh)).check(matches(isDisplayed()))
    }

    @Test
    fun checkProgressBarIsShown() {
        onView(withId(R.id.shift_item_refresh)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.loading_bar_layout)).check(matches(isDisplayed()))
    }


    @Test
    fun checkIsTitleDisplayed() {
        onView(withId(R.id.shift_display_toolbar_title)).check(matches(withText("Available Shifts")))
    }
}