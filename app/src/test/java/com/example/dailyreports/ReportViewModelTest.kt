package com.example.dailyreports.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dailyreports.data.Report
import com.example.dailyreports.data.ReportDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class ReportViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val reportDao = mock(ReportDao::class.java)
    private val viewModel = ReportViewModel(reportDao)

    @Test
    fun `getAll reports returns correct list`() = runTest {
        val sampleReports = listOf(
            Report(title = "Rapport 1", content = "Tache 1", format = "PDF"),
            Report(title = "Rapport 2", content = "Tache 2", format = "TXT")
        )

        `when`(reportDao.getAll()).thenReturn(flowOf(sampleReports))

        val reportsFlow = viewModel.reports
        reportsFlow.collect { reports ->
            assertEquals(2, reports.size)
            assertEquals("Rapport 1", reports[0].title)
        }
    }

    @Test
    fun `insert report calls dao insert`() = runTest {
        val report = Report(title = "Test", content = "Test content", format = "PDF")
        viewModel.insert(report)
        verify(reportDao).insert(report)
    }
}
