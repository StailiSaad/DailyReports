package com.example.dailyreports

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dailyreports.R
import com.example.dailyreports.ui.ReportListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReportListActivityUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(ReportListActivity::class.java)

    @Test
    fun testReportListScreen() {
        // Vérifier le titre
        Espresso.onView(ViewMatchers.withText("Rapports Journaliers"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Vérifier le RecyclerView est présent
        Espresso.onView(ViewMatchers.withId(R.id.rv_reports))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testEmptyStateMessage() {
        // Si aucun rapport, vérifier le message d'état vide
        Espresso.onView(ViewMatchers.withId(R.id.tv_empty))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withText("Aucun rapport disponible"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testReportItemClick() {
        // Si des rapports existent, cliquer sur le premier
        // (Ce test nécessite des données de test pré-chargées)
    }
}