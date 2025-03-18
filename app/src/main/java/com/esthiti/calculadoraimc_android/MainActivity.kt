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
import com.google.android.material.slider.Slider
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    // Inicialización tardía
    // PESO
    lateinit var btnSubstractWeight : Button
    lateinit var btnAddWeight : Button
    lateinit var tvWeight : TextView

    // ALTURA
    lateinit var sliderHeight : Slider
    lateinit var tvHeight : TextView

    // RESULTADO
    lateinit var btnReset : Button
    lateinit var btnCalculate : Button
    lateinit var tvResult : TextView

    var weight = 65.0f
    var height = 170.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnSubstractWeight = findViewById(R.id.buttonMinus)
        btnAddWeight = findViewById(R.id.buttonAdd)
        tvWeight = findViewById(R.id.tvInitialWeight)
        sliderHeight = findViewById(R.id.heightSlider)
        tvHeight = findViewById(R.id.tvInitialHeight)
        btnReset = findViewById(R.id.btnReset)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)

        // Comportamiento botón restar peso
        btnSubstractWeight.setOnClickListener {
            // Recuperamos el peso
            weight --
            tvWeight.text = "${weight.toInt()} kg"
        }

        // Comportamiento botón restar peso
        btnAddWeight.setOnClickListener {
            // Recuperamos el peso
            weight ++
            tvWeight.text = "${weight.toInt()} kg"
        }

        sliderHeight.addOnChangeListener { slider, value, fromUser ->
            height = value
            tvHeight.text = "${height.toInt()} cm"
        }

        btnCalculate.setOnClickListener {

            var result = (weight / (height/100).pow(2))

            tvResult.text = String.format("%.2f", result)

        }

        btnReset.setOnClickListener {

            tvWeight.text = "$weight kg"
            tvHeight.text = "${R.string.initial_height} cm"
            sliderHeight.value = 170f
            tvResult.text = R.string.resultado_inicial.toString()

        }
    }
}