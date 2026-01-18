package com.example.dailyreports

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.dailyreports.ui.ReportListActivity
import com.example.dailyreports.ui.SettingsActivity
import com.example.dailyreports.worker.ReportWorker
import com.example.dailyreports.utils.PreferencesManager
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        findViewById<Button>(R.id.btnReports).setOnClickListener {
            startActivity(Intent(this, ReportListActivity::class.java))
        }

        findViewById<Button>(R.id.btnStart).setOnClickListener {
            if (checkPermissions()) scheduleWorker()
        }
    }

    private fun checkPermissions(): Boolean {
        val perms = mutableListOf<String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            perms.add(Manifest.permission.POST_NOTIFICATIONS)

        return if (perms.isEmpty() || EasyPermissions.hasPermissions(this, *perms.toTypedArray()))
            true
        else {
            EasyPermissions.requestPermissions(
                this,
                "L'application a besoin de permission pour envoyer des notifications.",
                PERMISSION_REQUEST_CODE,
                *perms.toTypedArray()
            )
            false
        }
    }

    private fun scheduleWorker() {
        val prefs = PreferencesManager(this)
        val delay = prefs.time() - System.currentTimeMillis()

        if (delay <= 0) {
            Toast.makeText(this, "Heure invalide, génération immédiate pour test", Toast.LENGTH_SHORT).show()
        }

        val request = OneTimeWorkRequestBuilder<ReportWorker>()
            .setInitialDelay(if (delay > 0) delay else 0, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(this).enqueue(request)
        Toast.makeText(this, "Rapport planifié", Toast.LENGTH_SHORT).show()
    }
}
