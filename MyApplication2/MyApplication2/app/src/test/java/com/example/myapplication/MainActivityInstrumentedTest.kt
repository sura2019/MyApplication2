package com.example.myapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityInstrumentedTest {

    // Launches MainActivity before each test
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testUIComponentsVisible() {
        // Check if the School List text is displayed
        onView(withText("School List"))
            .check(matches(isDisplayed()))

        // Check if the RecyclerView is displayed
        onView(withId(R.id.recyclerViewSchools))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testItemClick() {
        // Click on the first item in the RecyclerView
        onView(withId(R.id.recyclerViewSchools))
            .perform(click())

        // Check if the overview paragraph is displayed
        onView(withId(R.id.textViewOverviewParagraph))
            .check(matches(isDisplayed()))
    }
}
