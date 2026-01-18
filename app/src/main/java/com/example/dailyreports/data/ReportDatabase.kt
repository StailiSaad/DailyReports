package com.example.dailyreports.data

import android.content.Context
import androidx.room.*

@Database(entities = [Report::class], version = 1)
@TypeConverters(Converters::class)
abstract class ReportDatabase : RoomDatabase() {
    abstract fun reportDao(): ReportDao

    companion object {
        @Volatile
        private var INSTANCE: ReportDatabase? = null

        fun get(ctx: Context): ReportDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    ctx.applicationContext,
                    ReportDatabase::class.java,
                    "reports.db"
                ).build().also { INSTANCE = it }
            }
    }
}
