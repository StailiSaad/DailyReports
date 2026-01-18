package com.example.dailyreports.worker

import android.Manifest
import android.os.Build
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.dailyreports.data.Report
import com.example.dailyreports.data.ReportDatabase
import com.example.dailyreports.utils.FileGenerator
import com.example.dailyreports.utils.NotificationHelper
import com.example.dailyreports.utils.PreferencesManager
import pub.devrel.easypermissions.EasyPermissions

class ReportWorker(
    ctx: android.content.Context,
    params: WorkerParameters
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        return try {
            val prefs = PreferencesManager(applicationContext)
            val tasks = prefs.tasks()
            val content = tasks.joinToString(separator = "\n- ", prefix = "Tâches du jour:\n- ")


            val filePath = FileGenerator.generatePdf(applicationContext, content)
                ?: return Result.failure()


            ReportDatabase.get(applicationContext).reportDao().insert(
                Report(
                    title = "Rapport Journalier",
                    content = content,
                    format = "PDF",
                    filePath = filePath
                )
            )


            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
                EasyPermissions.hasPermissions(applicationContext, Manifest.permission.POST_NOTIFICATIONS)
            ) {
                NotificationHelper.show(applicationContext)
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}
