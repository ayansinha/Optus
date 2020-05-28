package org.techm.optus.ui.view.user


import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
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
    private val id = "ID : 7"
    private val name = "Name : Kurtis Weissnat"
    private val email = "Email : Telly.Hoeger@billy.biz"
    private val phone = "Phone : 210.067.6132"

    @Before
    fun setUp() {
        activityRule.launchActivity(intent)
    }

    /**
     * check if app launch successfully
     */
    @Test
    fun appLaunchSuccessfully() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    /**
     * check if {user fragment} is Displayed
     */
    @Test
    fun checkUserFragmentIsDisplayed() {
        val fragmentUser = FragmentUser()
        activityRule.activity.supportFragmentManager.beginTransaction()
            .add(R.id.fragment, fragmentUser).commit()
    }

    /**
     * check if recyclerview of {user fragment} is Displayed
     */
    @Test
    fun recyclerViewFragmentUserIsDisplayed() {
        onView(withId(R.id.recyclerViewUser)).check(matches(isDisplayed()))
    }

    /**
     * check if progressbar of {user fragment} is Displayed
     */
    @Test
    fun onLaunchCheckProgressBarIsDisplayed() {
        IdlingResource.ResourceCallback {
            onView(withId(R.id.progressBarUser))
                .check(matches(isDisplayed()))
        }
    }

    /**
     * check if recyclerview of {user fragment} with text view user id matches
     */
    @Test
    fun testRecyclerViewFragmentUserHasTextViewUserID() {
        IdlingResource.ResourceCallback {
            onView(withId(R.id.recyclerViewUser))
                .check(matches(hasDescendant(withId(R.id.textViewUserID))))
                .check(matches(withText(name)))
        }
    }

    /**
     * check if recyclerview of {user fragment} with text view user name matches
     */
    @Test
    fun testRecyclerViewFragmentUserHasTextViewUserName() {
        IdlingResource.ResourceCallback {
            onView(withId(R.id.recyclerViewUser))
                .check(matches(hasDescendant(withId(R.id.textViewUserName))))
                .check(matches(withText(name)))
        }
    }

    /**
     * check if recyclerview of {user fragment} with text view user email matches
     */
    @Test
    fun testRecyclerViewFragmentUserHasTextViewUserEmail() {
        IdlingResource.ResourceCallback {
            onView(withId(R.id.recyclerViewUser))
                .check(matches(hasDescendant(withId(R.id.textViewUserEmail))))
                .perform(actionOnItemAtPosition<UserViewHolder>(listItemPosition , typeText(email)))
        }
    }

    /**
     * check if recyclerview of {user fragment} with text view user phone matches
     */
    @Test
    fun testRecyclerViewFragmentUserHasTextViewUserPhone() {
        IdlingResource.ResourceCallback {
            onView(withId(R.id.recyclerViewUser))
                .check(matches(hasDescendant(withId(R.id.textViewUserPhone))))
                .check(matches(withText(phone)))
        }
    }

    /**
     * check if recyclerview of {user fragment} scroll at position
     */
    @Test
    fun testRecyclerViewFragmentUserTestScrollToPosition() {
        onView(withId(R.id.recyclerViewUser))
            .perform(RecyclerViewActions.scrollToPosition<UserViewHolder>(listItemPosition))
    }

    /**
     * check if recyclerview of {user fragment} is clicked
     */
    @Test
    fun testRecyclerviewUserOnClickItem() {
        onView(withId(R.id.recyclerViewUser))
            .perform(actionOnItemAtPosition<UserViewHolder>(listItemPosition, click()))
    }
}