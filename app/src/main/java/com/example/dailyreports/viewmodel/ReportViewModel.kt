package com.example.dailyreports.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyreports.data.Report
import com.example.dailyreports.data.ReportDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val reportDao: ReportDao
) : ViewModel() {
    val reports = reportDao.getAll()

    fun insert(report: Report) = viewModelScope.launch { reportDao.insert(report) }
    fun update(report: Report) = viewModelScope.launch { reportDao.update(report) }
    fun delete(report: Report) = viewModelScope.launch { reportDao.delete(report) }
}

