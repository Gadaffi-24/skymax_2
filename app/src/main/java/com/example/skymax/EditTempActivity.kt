package com.example.skymax

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class EditTempActivity : AppCompatActivity() {

    private lateinit var editTextList: MutableList<EditText>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_temp)

        val tempArray = intent.getIntArrayExtra("temps") ?: IntArray(7)
        val layout = findViewById<LinearLayout>(R.id.Inputs)
        val saveButton = findViewById<Button>(R.id.btnSaveTemps)

        editTextList = mutableListOf()

        val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        for (i in tempArray.indices) {
            val editText = EditText(this).apply {
                hint = "Enter temp for ${days[i]}"
                setText(tempArray[i].toString())
                inputType = InputType.TYPE_CLASS_NUMBER
            }
            layout.addView(editText)
            editTextList.add(editText)
        }

        saveButton.setOnClickListener {
            val newTemps = editTextList.map {
                it.text.toString().toIntOrNull() ?: 0
            }.toIntArray()

            val resultIntent = Intent()
            resultIntent.putExtra("newTemps", newTemps)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
