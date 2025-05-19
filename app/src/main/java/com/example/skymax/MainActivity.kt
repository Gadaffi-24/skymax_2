package com.example.skymax

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private var maxTemps = arrayOf(25, 29, 22, 24, 20, 18, 16)
    private val conditions = arrayOf("Sunny", "Rainy", "Cloudy", "Sunny", "Windy", "Rainy", "Snowy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tvWeatherInfo)
        val backBtn = findViewById<Button>(R.id.btnBack)
        val editBtn = findViewById<Button>(R.id.btnEditTemps)

        showWeatherInfo(textView)

        backBtn.setOnClickListener {
            finish() // Return to WelcomeActivity
        }

        editBtn.setOnClickListener {
            val intent = Intent(this, EditTempActivity::class.java)
            intent.putExtra("temps", maxTemps.toIntArray())
            startActivityForResult(intent, 100)
        }
    }

    private fun showWeatherInfo(textView: TextView) {
        var displayText = ""
        var sum = 0
        for (i in days.indices) {
            displayText += "${days[i]}: ${maxTemps[i]}°C – ${conditions[i]}\n"
            sum += maxTemps[i]
        }
        val average = sum / maxTemps.size
        displayText += "\nAverage Max Temperature: $average°C"
        textView.text = displayText
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val updatedTemps = data?.getIntArrayExtra("newTemps")
            if (updatedTemps != null) {
                maxTemps = updatedTemps.toTypedArray()
                val textView = findViewById<TextView>(R.id.tvWeatherInfo)
                showWeatherInfo(textView)
            }
        }
    }
}
