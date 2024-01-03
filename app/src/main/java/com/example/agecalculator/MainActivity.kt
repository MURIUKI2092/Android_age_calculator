package com.example.agecalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
    // set it as a nullable here
    private var selectedDate :TextView? = null
    private var dateInMillis: TextView? = null

    private fun showDatePickerDialog(){

        selectedDate= findViewById<TextView>(R.id.dateStr)
        dateInMillis = findViewById<TextView>(R.id.dateMin)
        val datePickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view:DatePicker,year: Int,month: Int,day: Int ->
            val theDate = "$day/${month+1}/$year"
            selectedDate?.text=theDate
            // the standard formatter
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val dateParsed = sdf.parse(theDate)
            dateParsed?.let {
                val currentDate = sdf.parse((sdf.format(System.currentTimeMillis())))
                currentDate?.let {
                    val currentDateInMin = currentDate.time / 60000
                    val inMillis = dateParsed.time / 60000
                    dateInMillis?.text = "${currentDateInMin - inMillis}"
                }
            }

        },calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
        // only show those in the past
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()-86400000
        // Show the date picker dialog
        datePickerDialog.show()


    }

}