package com.example.dailyreports.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(report: Report)

    @Update
    suspend fun update(report: Report)

    @Delete
    suspend fun delete(report: Report)

    @Query("SELECT * FROM reports ORDER BY id DESC") // <- lowercase "reports"
    fun getAll(): Flow<List<Report>>

    @Query("SELECT * FROM reports") // <- lowercase "reports"
    suspend fun getAllOnce(): List<Report>
}

