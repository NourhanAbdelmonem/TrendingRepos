package com.example.aqwastask

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.aqwastask.framework.presentation.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TrendingReposScreenTest {

    lateinit var scenario: ActivityScenario<MainActivity>
    lateinit var activity: MainActivity

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            activity = it
        }
    }

    @Test
    fun testTrendingRepos() {
        onView(withId(R.id.trendingTV)).check(matches(isDisplayed()))
        onView(withId(R.id.ic_more)).check(matches(isDisplayed()))
        Thread.sleep(4000)
        if (activity.findViewById<RecyclerView>(R.id.reposRV).adapter!!.itemCount > 0) {
            onView(withId(R.id.reposRV)).check(matches(isDisplayed()))
            onView(withId(R.id.reposRV)).perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        } else
            onView(withId(R.id.error_layout)).check(matches(isDisplayed()))
    }

}