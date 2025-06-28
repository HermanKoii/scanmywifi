package com.example.wifiscanner

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentationTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test_ui_components_displayed() {
        // Verify scan button is displayed
        onView(withId(R.id.btn_scan_networks))
            .check(matches(isDisplayed()))

        // Verify WiFi networks RecyclerView is displayed
        onView(withId(R.id.rv_wifi_networks))
            .check(matches(isDisplayed()))
    }
}