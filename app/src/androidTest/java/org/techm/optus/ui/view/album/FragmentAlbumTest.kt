package org.techm.optus.ui.view.album

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.techm.optus.R
import org.techm.optus.ui.adapter.album.AlbumViewHolder
import org.techm.optus.ui.adapter.user.UserViewHolder
import org.techm.optus.ui.view.MainActivity

class FragmentAlbumTest {

    @get: Rule
    val activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    private val listItemPosition = 5

    @Before
    fun setUp() {
        val intent = Intent()
        activityRule.launchActivity(intent)
    }

    @Test
    fun testFragmentAlbumProgressBarIsDisplayed() {

        onView(withId(R.id.recyclerViewUser))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserViewHolder>(
                    listItemPosition,
                    ViewActions.click()
                )
            )

        IdlingResource.ResourceCallback {
            onView(withId(R.id.progressBarUser))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun testAlbumFragmentIsDisplayed() {
        onView(withId(R.id.recyclerViewUser))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserViewHolder>(
                    listItemPosition,
                    ViewActions.click()
                )
            )

        onView(withId(R.id.recyclerViewAlbum))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewFragmentAlbumTestScrollingToPosition() {
        onView(withId(R.id.recyclerViewUser))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserViewHolder>(
                    listItemPosition,
                    ViewActions.click()
                )
            )
        onView(withId(R.id.recyclerViewAlbum))
            .perform(RecyclerViewActions.scrollToPosition<UserViewHolder>(listItemPosition))
    }

    @Test
    fun testRecyclerviewAlbumOnClickItem() {
        onView(withId(R.id.recyclerViewUser))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UserViewHolder>(
                    listItemPosition,
                    ViewActions.click()
                )
            )
        onView(withId(R.id.recyclerViewAlbum))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AlbumViewHolder>(
                    listItemPosition,
                    ViewActions.click()
                )
            )
    }
}