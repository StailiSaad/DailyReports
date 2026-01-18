package com.example.dailyreports.di

import android.content.Context
import com.example.dailyreports.data.ReportDao
import com.example.dailyreports.data.ReportDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ReportDatabase {
        return ReportDatabase.get(context)
    }

    @Provides
    @Singleton
    fun provideReportDao(database: ReportDatabase): ReportDao {
        return database.reportDao()
    }
}
