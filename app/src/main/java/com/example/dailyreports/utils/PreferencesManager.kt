package com.example.dailyreports.utils

import android.content.Context

class PreferencesManager(ctx: Context) {
    private val p = ctx.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun save(time: Long, format: String, tasks: Set<String>) =
        p.edit().putLong("time", time)
            .putString("format", format)
            .putStringSet("tasks", tasks)
            .apply()

    fun time() = p.getLong("time", 0)
    fun format() = p.getString("format", "PDF")!!
    fun tasks() = p.getStringSet("tasks", emptySet())!!
}
