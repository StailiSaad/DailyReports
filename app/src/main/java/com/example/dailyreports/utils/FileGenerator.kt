package com.example.dailyreports.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object FileGenerator {

    fun generatePdf(ctx: Context, content: String): String? {
        val pdf = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdf.startPage(pageInfo)
        val canvas: Canvas = page.canvas
        val paint = Paint().apply { textSize = 16f; isAntiAlias = true }

        var y = 50f
        content.split("\n").forEach { line ->
            canvas.drawText(line, 40f, y, paint)
            y += paint.textSize + 10
        }

        pdf.finishPage(page)

        // Save file with timestamp
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val file = File(ctx.filesDir, "report_$timestamp.pdf")

        return try {
            pdf.writeTo(FileOutputStream(file))
            pdf.close()
            file.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
