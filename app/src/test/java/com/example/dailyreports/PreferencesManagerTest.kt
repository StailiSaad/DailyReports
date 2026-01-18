package com.example.dailyreports.utils

import android.content.Context
import android.content.SharedPreferences
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class PreferencesManagerTest {

    private lateinit var prefs: SharedPreferences
    private lateinit var manager: PreferencesManager
    private val context = mock(Context::class.java)

    @Before
    fun setup() {
        prefs = mock(SharedPreferences::class.java)
        val editor = mock(SharedPreferences.Editor::class.java)

        `when`(context.getSharedPreferences("prefs", 0)).thenReturn(prefs)
        `when`(prefs.edit()).thenReturn(editor)
        `when`(editor.putLong(anyString(), anyLong())).thenReturn(editor)
        `when`(editor.putString(anyString(), anyString())).thenReturn(editor)
        `when`(editor.putStringSet(anyString(), anySet())).thenReturn(editor)

        manager = PreferencesManager(context)
    }

    @Test
    fun `save and retrieve format`() {
        manager.save(1000L, "PDF", setOf("Task1"))
        assertEquals("PDF", manager.format())
    }
}
