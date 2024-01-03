package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker :Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            showDatePickerDialog()
            Toast.makeText(this,"btn date picker pressed", Toast.LENGTH_LONG).show()
        }

    }
    private val calendar = Calendar.getInstance()

    private fun showDatePickerDialog(){

        val selectedDate= findViewById<TextView>(R.id.dateStr)
        val dateInMillis = findViewById<TextView>(R.id.dateMin)
        val datePickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view:DatePicker,year: Int,month: Int,day: Int ->
            val theDate = "$day/${month+1}/$year"
            selectedDate.text = theDate

            val inMillis= calculateTimeDifference(day, month, year)
            dateInMillis.text="$inMillis"
        },calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
        // Show the date picker dialog
        datePickerDialog.show()


    }
    private fun calculateTimeDifference(day: Int, month: Int, year: Int): Long {
        val currentDate = Calendar.getInstance()
        val selectedDate = Calendar.getInstance()
        // month in a calendar is usually 0-based
        selectedDate.set(year, month - 1, day)

        //calculate the time difference in milliseconds
        val timeDiffInMillis = abs(currentDate.timeInMillis - selectedDate.timeInMillis)
        return timeDiffInMillis / 60000L


    }

}