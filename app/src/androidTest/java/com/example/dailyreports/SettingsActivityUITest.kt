package com.example.dailyreports

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dailyreports.ui.SettingsActivity
import com.example.dailyreports.ui.SettingsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsActivityUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SettingsActivity::class.java)

    @Test
    fun testSettingsScreenElements() {
        // Vérifier le titre
        Espresso.onView(ViewMatchers.withText("Paramètres"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Vérifier les formats disponibles
        Espresso.onView(ViewMatchers.withText("PDF"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withText("CSV"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withText("TXT"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testFormatSelection() {
        // Sélectionner le format CSV
        Espresso.onView(ViewMatchers.withText("CSV"))
            .perform(ViewActions.click())

        // Vérifier que CSV est sélectionné
        // (Dépend de votre implémentation UI)
    }

    @Test
    fun testTimePickerInteraction() {
        // Cliquer sur le bouton de sélection d'heure
        Espresso.onView(ViewMatchers.withText("Changer l'heure"))
            .perform(ViewActions.click())

        // Vérifier que le TimePicker apparaît
        // (Ce test nécessite de gérer le TimePickerDialog)
    }
}