package com.esthiti.calculadoraimc_android

import android.os.Bundle
import android.widget.Button
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
    lateinit var tvResult : TextView
    lateinit var tvResultDescription : TextView

    lateinit var btnReset : Button
    lateinit var btnCalculate : Button

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
        tvResultDescription = findViewById(R.id.tvResultDescription)

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

            var result = (weight / (height / 100).pow(2))

            tvResult.text = String.format("%.2f", result)

            var colorId = 0
            var descriptionId = 0
            when (result){
                in 0f..< 18.5f ->{
                    colorId = getColor(R.color.bmi_underweight)
                    descriptionId = R.string.bmi_underweight
                }
                in 18.5f..24.9f ->{
                    colorId = R.color.bmi_normal
                    descriptionId = R.string.bmi_normal
                }
                in 25f..< 29.9f ->{
                    colorId = R.color.bmi_overweight
                    descriptionId = R.string.bmi_overweight
                }
                in 30f..< 34.9f ->{
                    colorId = R.color.bmi_obese
                    descriptionId = R.string.bmi_obese
                }
                else ->{
                    colorId = R.color.bmi_extremely_obese
                    descriptionId = R.string.bmi_extremely_obese
                }
            }
            tvResult.setTextColor(getColor(colorId))
            tvResultDescription.text = getString(descriptionId)
            tvResultDescription.setTextColor(getColor(colorId))
        }

        btnReset.setOnClickListener {

            tvWeight.text = getString(R.string.default_weight_text)
            tvHeight.text = getString(R.string.default_height_text)
            sliderHeight.value = getString(R.string.default_height).toFloat()
            tvResult.text = getString(R.string.initial_result)
            tvResult.setTextColor(getColor(R.color.bmi_underweight))
            tvResultDescription.text = getString(R.string.bmi_underweight)
            tvResultDescription.setTextColor(getColor(R.color.bmi_underweight))

        }
    }

    companion object {
        const val WEIGHT = 65.0f
    }
}