package org.techm.optus.ui.view.album

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.techm.optus.R
import org.techm.optus.ui.adapter.album.AlbumViewHolder
import org.techm.optus.ui.adapter.user.UserViewHolder
import org.techm.optus.ui.view.MainActivity

class FragmentAlbumDetailsTest {

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
    fun testAlbumFragmentDetailsIsDisplayed() {
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
        IdlingResource.ResourceCallback {
            onView(withText(R.id.textViewAlbumDetailTitle))
                .check(ViewAssertions.matches(isDisplayed()))
        }
    }
}