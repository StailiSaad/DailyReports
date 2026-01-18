package com.example.dailyreports.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "reports")
data class Report(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val format: String,
    val filePath: String = "",
    val date: Date = Date(),
    val isModified: Boolean = false
)
