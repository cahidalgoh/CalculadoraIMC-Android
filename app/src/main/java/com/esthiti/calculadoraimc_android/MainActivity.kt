package com.esthiti.calculadoraimc_android

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    // Inicialización tardía
    lateinit var weightEditText : EditText
    lateinit var heightEditText : EditText
    lateinit var calculateButton : Button
    lateinit var resultadoTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        weightEditText = findViewById(R.id.weightEditText)
        heightEditText = findViewById(R.id.heightEditText)
        calculateButton = findViewById(R.id.calcularBtn)
        resultadoTextView = findViewById(R.id.resultadoTextView)
        

        calculateButton.setOnClickListener {
            val weight = weightEditText.text.toString().toFloat()
            val height =heightEditText.text.toString().toFloat()

            var resultado = (height / (height/100).pow(2)).toFloat()

            resultadoTextView.text = "Resultado:\n" + String.format("%.2f", resultado)
            println("Peso: $weight\nAltura: $height")

            Log.i("IMC", "El resulltado : $resultado")
        }
    }
}