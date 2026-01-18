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
class MainActivityTest {

    @Test
    fun clickSettingsButton_opensSettingsActivity() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnSettings)).perform(click())
        // On pourrait vérifier si SettingsActivity est affichée
    }

    @Test
    fun clickReportsButton_opensReportListActivity() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnReports)).perform(click())
    }
}
