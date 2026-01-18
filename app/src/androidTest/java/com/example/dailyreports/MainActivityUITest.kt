package com.example.dailyreports

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testMainScreenElementsDisplayed() {
        // Vérifier que le titre est affiché
        Espresso.onView(ViewMatchers.withText("Générateur de Rapports Journaliers"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Vérifier que les boutons sont présents
        Espresso.onView(ViewMatchers.withText("Commencer"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withText("Voir les rapports"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withText("Paramètres"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testNavigationToReports() {
        // Cliquer sur le bouton "Voir les rapports"
        Espresso.onView(ViewMatchers.withText("Voir les rapports"))
            .perform(ViewActions.click())

        // Vérifier qu'on est sur la bonne activité
        // (Adapter selon votre implémentation de navigation)
    }

    @Test
    fun testNavigationToSettings() {
        // Cliquer sur le bouton "Paramètres"
        Espresso.onView(ViewMatchers.withText("Paramètres"))
            .perform(ViewActions.click())

        // Vérifier qu'on est sur l'écran des paramètres
        // (Adapter selon votre implémentation)
    }

    @Test
    fun testStartButtonFunctionality() {
        // Cliquer sur le bouton "Commencer"
        Espresso.onView(ViewMatchers.withText("Commencer"))
            .perform(ViewActions.click())

        // Vérifier que la permission est demandée
        // (Ce test nécessite de gérer le dialogue de permission)
    }
}