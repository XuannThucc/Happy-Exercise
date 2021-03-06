package com.example.happyexercise

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_b_m_i.*
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    val METRIC_UNIT_VIEW = "METRIC_UNIT_VIEW"
    val US_UNIT_VIEW = "US_UNIT_VIEW"
    var currentUnitView: String = METRIC_UNIT_VIEW
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_i)
        setSupportActionBar(toolbar_BMI_activity)
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)//hien nut back
            actionbar.title = "CALCULATE BMI"

        }
        toolbar_BMI_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btnBMIOk.setOnClickListener {
            if(currentUnitView.equals(METRIC_UNIT_VIEW)){
                if (validateMetricUnit()) {
                    val heightValue: Float = etMetricUnitHeight.text.toString().toFloat() / 100
                    val weightValue: Float = etMetricUnitWeight.text.toString().toFloat()
                    val bmi = weightValue / (heightValue * heightValue)
                    displayBmiResult(bmi)
                } else {
                    Toast.makeText(this@BMIActivity, "Please enter valid values", Toast.LENGTH_LONG)
                        .show()
                }
            }else{
                if(validateUsUnit()){
                    val heightValue: Float = etUsUnitHeightFeet.text.toString().toFloat() * 12 + etUsUnitHeightInch.text.toString().toFloat()
                    val weightValue: Float = etUsUnitWeight.text.toString().toFloat()
                    val bmi = 703 *( weightValue / (heightValue * heightValue))
                    displayBmiResult(bmi)
                } else {
                    Toast.makeText(this@BMIActivity, "Please enter valid values", Toast.LENGTH_LONG)
                        .show()
                }
            }


        }

        mekaVisiblieMetricUnitView()
        rgGroupRaido.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rbMetricUnit) {
                mekaVisiblieMetricUnitView()
            }
            if(checkedId == R.id.rbEuUnit){
                mekaVisiblieUsUnitView()
            }
        }

    }

    private fun mekaVisiblieMetricUnitView() {
        currentUnitView = METRIC_UNIT_VIEW
        llUnitView.visibility = View.VISIBLE
        llUsUnitView.visibility = View.GONE
        llDisplayBMIresult.visibility = View.GONE
    }

    private fun mekaVisiblieUsUnitView() {
        currentUnitView = US_UNIT_VIEW
        llUnitView.visibility = View.GONE
        llUsUnitView.visibility = View.VISIBLE
        llDisplayBMIresult.visibility = View.GONE
    }

    private fun displayBmiResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String
        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }
        llDisplayBMIresult.visibility = View.VISIBLE
        tvYourBMI.visibility = View.VISIBLE
        tvBMIValue.visibility = View.VISIBLE
        tvBMIType.visibility = View.VISIBLE
        tvBMIDesciption.visibility = View.VISIBLE
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        tvYourBMI.text = "YOUR BMI"
        tvBMIValue.text = bmiValue
        tvBMIType.text = bmiLabel
        tvBMIDesciption.text = bmiDescription
    }

    private fun validateMetricUnit(): Boolean {
        var isValid = true
        if (etMetricUnitWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (etMetricUnitHeight.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }
    private fun validateUsUnit() : Boolean{
        var isVail = true
        if(etUsUnitWeight.text.toString().isEmpty()){
            isVail = false
        } else if(etUsUnitHeightFeet.text.toString().isEmpty()){
            isVail = false
        }else if(etUsUnitHeightInch.text.toString().isEmpty()){
            isVail = false
        }
        return isVail
    }
}