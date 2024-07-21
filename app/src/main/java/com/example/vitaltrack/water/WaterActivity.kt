package com.example.vitaltrack.water

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vitaltrack.R

class WaterActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_water)


        val weightEditText = findViewById<EditText>(R.id.weightEditText)
        val ageEditText = findViewById<EditText>(R.id.ageEditText)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val waterCircleResult = findViewById<TextView>(R.id.waterCircle_result)

        calculateButton.setOnClickListener {
            val weight = weightEditText.text.toString().toDoubleOrNull()
            val age = ageEditText.text.toString().toIntOrNull()

            if (weight != null && age != null) {
                val waterIntake = calculateWaterIntake(weight, age)
                val formattedIntake = String.format("%.2f", waterIntake)
                resultTextView.text = "Você deve beber $formattedIntake litros de água por dia."
                waterCircleResult.text = "$formattedIntake L"
            } else {
                resultTextView.text = "Por favor, insira valores válidos."
                waterCircleResult.text = "0.00 L"
            }
        }
    }

    private fun calculateWaterIntake(weight: Double, age: Int): Double {
        // Fórmula simplificada para cálculo de ingestão de água
        val baseIntake = weight * 0.033
        val ageFactor = when {
            age < 30 -> 0.4
            age in 30..55 -> 0.35
            else -> 0.3
        }
        return baseIntake * (1 + ageFactor)
    }
}