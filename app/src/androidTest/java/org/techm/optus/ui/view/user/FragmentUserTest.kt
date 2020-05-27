package org.techm.optus.ui.view.user


import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.techm.optus.R
import org.techm.optus.ui.adapter.user.UserViewHolder
import org.techm.optus.ui.view.MainActivity


class FragmentUserTest {

    @get: Rule
    val activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    private val listItemPosition = 7
    private val intent = Intent()

    @Before
    fun setUp() {
        activityRule.launchActivity(intent)
    }

    @Test
    fun appLaunchSuccessfully() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun checkUserFragmentIsDisplayed() {
        val fragmentUser = FragmentUser()
        activityRule.activity.supportFragmentManager.beginTransaction()
            .add(R.id.fragment, fragmentUser).commit()
    }

    @Test
    fun recyclerViewFragmentUserIsDisplayed() {
        onView(withId(R.id.recyclerViewUser)).check(matches(isDisplayed()))
    }

    @Test
    fun onLaunchCheckProgressBarIsDisplayed() {
        IdlingResource.ResourceCallback {
            onView(withId(R.id.progressBarUser))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun testRecyclerViewFragmentUserTestScrollToPosition() {
        onView(withId(R.id.recyclerViewUser))
            .perform(RecyclerViewActions.scrollToPosition<UserViewHolder>(listItemPosition))
    }

    @Test
    fun testRecyclerviewUserOnClickItem() {
        onView(withId(R.id.recyclerViewUser))
            .perform(actionOnItemAtPosition<UserViewHolder>(listItemPosition, click()))
    }


}