package com.example.dailyreports.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dailyreports.R
import com.example.dailyreports.ReportDetailActivity
import com.example.dailyreports.data.ReportDatabase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ReportListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_list)

        val list = findViewById<ListView>(R.id.listReports)
        val db = ReportDatabase.Companion.get(this)

        lifecycleScope.launch {
            db.reportDao().getAll().collectLatest { reports ->
                list.adapter = ArrayAdapter(
                    this@ReportListActivity,
                    android.R.layout.simple_list_item_1,
                    reports.map { r -> "${r.title}\n${r.content}" } // include tasks
                )

                list.setOnItemClickListener { _, _, position, _ ->
                    val report = reports[position]
                    val intent = Intent(this@ReportListActivity, ReportDetailActivity::class.java)
                    intent.putExtra("report_file_path", report.filePath)
                    startActivity(intent)
                }
            }
        }

    }
}