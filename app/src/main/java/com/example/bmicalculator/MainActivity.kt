package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

       calcButton.setOnClickListener{

           val weight = weightText.text.toString()
           val height = heightText.text.toString()
           if(validateInput(weight,height)) {

               val bmi = weight.toFloat() / ((height.toFloat() / 100) * ((height.toFloat() / 100)))
               val bmi2Digits = String.format("%.2f", bmi).toFloat()
               displayResult(bmi2Digits)
           }
       }
    }

    private fun validateInput(weight:String?,height:String?):Boolean {
        return when{
            weight.isNullOrEmpty()-> {
                Toast.makeText(this,"Weight is Empty",Toast.LENGTH_LONG).show()
                return false
            }
                height.isNullOrEmpty()-> {
                    Toast.makeText(this, "height is Empty", Toast.LENGTH_LONG).show()
                    return false
                }
            else ->{
                return true
            }

        }
    }


    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvindex)
        val resultDescription = findViewById<TextView>(R.id.textresult)
        val info = findViewById<TextView>(R.id.tvinfo)

        resultIndex.text = bmi.toString()
        info.text = "Normal range is 18.5 - 24.9"

        var resultText = ""
        var color = 0

        when{
            bmi<18.50 ->{
                resultText = "UnderWeight"
                color =R.color.UnderWeight
            }
            bmi in 18.50..24.99->{
                resultText = "Healthy"
                color = R.color.Normal
            }
            bmi in 25.00..29.99->{
                resultText = "OverWeight"
                color=R.color.Overweight
            }
            bmi>29.99->{
                resultText= "OBSESE"
                color=R.color.Obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text =resultText





    }
}