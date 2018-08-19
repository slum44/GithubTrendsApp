package lum.steve.githubtrendsapp.repos.trend

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TrendingReposActivityTest {

    @Rule
    @JvmField var rule = ActivityTestRule(TrendingReposActivity::class.java)

    @Test
    fun repos_DisplayedInUi() {
        onView(withText("repo1 desc")).check(ViewAssertions.matches(isDisplayed()))
        onView(withText("repo2 desc")).check(ViewAssertions.matches(isDisplayed()))
    }

}