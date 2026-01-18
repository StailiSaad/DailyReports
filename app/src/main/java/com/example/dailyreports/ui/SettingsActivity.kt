package com.example.dailyreports.ui

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.dailyreports.R
import com.example.dailyreports.tasks.TaskRepository
import com.example.dailyreports.utils.PreferencesManager
import java.util.Calendar

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val prefs = PreferencesManager(this)
        val timeBtn = findViewById<Button>(R.id.btnTime)
        val saveBtn = findViewById<Button>(R.id.btnSave)
        val formatSpinner = findViewById<Spinner>(R.id.spinnerFormat)
        val taskLayout = findViewById<LinearLayout>(R.id.taskLayout)
        val selectedTasks = mutableSetOf<String>()


        TaskRepository.tasks.forEach {
            val cb = CheckBox(this)
            cb.text = it
            cb.setOnCheckedChangeListener { _, checked ->
                if (checked) selectedTasks.add(it) else selectedTasks.remove(it)
            }
            taskLayout.addView(cb)
        }


        timeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            TimePickerDialog(this, { _, h, m ->
                cal.set(Calendar.HOUR_OF_DAY, h)
                cal.set(Calendar.MINUTE, m)
                prefs.save(cal.timeInMillis, formatSpinner.selectedItem.toString(), selectedTasks)
                Toast.makeText(this, "Time saved", Toast.LENGTH_SHORT).show()
            }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        saveBtn.setOnClickListener { finish() }
    }
}
