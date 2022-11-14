package com.example.dobcalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvSelectedDate2: TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDataPicker: Button = findViewById(R.id.btnDataPicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedDate2 = findViewById(R.id.ageInMinute)

        btnDataPicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->

                //  Log.e("ABCD", "$day")

                Toast.makeText(
                    this,
                    "year was $selectedYear, month was ${selectedMonth + 1} ," +
                            " day of month was $selectedDayOfMonth",
                    Toast.LENGTH_LONG
                ).show()

                val selectedDate = "$selectedYear/${selectedMonth + 1}/$selectedDayOfMonth"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time / 60000

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvSelectedDate2?.text = differenceInMinutes.toString()
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}