package com.example.dailyreports

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dailyreports.data.ReportDatabase
import kotlinx.coroutines.launch
import java.io.File
import androidx.core.content.FileProvider

class ReportDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_detail)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvContent = findViewById<TextView>(R.id.tvContent)
        val tvFormat = findViewById<TextView>(R.id.tvFormat)
        val tvPath = findViewById<TextView>(R.id.tvPath)

        val filePath = intent.getStringExtra("report_file_path") ?: return

        // ✅ Use coroutine to call suspend function
        lifecycleScope.launch {
            val report = ReportDatabase.get(this@ReportDetailActivity)
                .reportDao()
                .getAllOnce()
                .firstOrNull { it.filePath == filePath }

            report?.let {
                tvTitle.text = it.title
                tvContent.text = it.content // tasks included
                tvFormat.text = "Format: ${it.format}"
                tvPath.text = "File: ${it.filePath}"
            }
        }

        // Optional: open the file on click
        tvPath.setOnClickListener {
            val file = File(filePath)
            if (file.exists()) {
                val uri = FileProvider.getUriForFile(
                    this,
                    "${packageName}.fileprovider",
                    file
                )
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(uri, "text/plain") // adjust MIME type if needed
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                startActivity(intent)
            }
        }
    }
}
