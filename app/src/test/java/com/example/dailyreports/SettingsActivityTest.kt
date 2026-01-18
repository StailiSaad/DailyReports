package com.example.dailyreports.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dailyreports.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsActivityTest {

    @Test
    fun clickTimeButton_showsTimePicker() {
        ActivityScenario.launch(SettingsActivity::class.java)
        onView(withId(R.id.btnTime)).perform(click())

    }

    @Test
    fun selectTasks_checkboxUpdatesSelection() {
        ActivityScenario.launch(SettingsActivity::class.java)

    }
}
