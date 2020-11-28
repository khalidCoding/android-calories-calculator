package com.khalidcoding.caloriescalculator.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.khalidcoding.caloriescalculator.R
import com.khalidcoding.caloriescalculator.databinding.ActivityCaloriesCalculatorBinding
import com.khalidcoding.caloriescalculator.utils.*

class CaloriesCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCaloriesCalculatorBinding
    private var level: Int = 0
    private var bmr : Double = 0.0
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories_calculator)

        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_calories_calculator
            ) as ActivityCaloriesCalculatorBinding
        binding.btnCalculate.setOnClickListener {
            val age = binding.edtAge.text.toString().toInt()
            val height = binding.edtHeight.text.toString().toInt()
            val weight = binding.edtWeight.text.toString().toInt()
            val sex = binding.radioGroup.checkedRadioButtonId
            showDialogMsg(messageCaloriesNeeded(age, weight,height, sex), messageBmr(),this)
        }

        setUpSpinner()

        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL


    }


    private fun setUpSpinner() {
        val items = resources.getStringArray(R.array.levels_array)
        val spinnerAdapter =
            object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items) {

                override fun isEnabled(position: Int): Boolean {
                    // Disable the first item from Spinner
                    // First item will be use for hint

                    return position != 0
                }

                override fun getDropDownView(
                    position: Int,
                    convertView: View?,
                    parent: ViewGroup
                ): View {
                    val view: TextView =
                        super.getDropDownView(position, convertView, parent) as TextView
                    //set the color of first item in the drop down list to gray
                    if (position == 0) {
                        view.setTextColor(Color.GRAY)
                    } else {

                    }
                    return view
                }


            }
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.levelsSpinner.adapter = spinnerAdapter

        binding.levelsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                level = position

            }

        }

    }


    private fun calculateCalories(
        age: Int,
        weight: Int,
        height: Int,
        sex: Int,
    ): Int {
        return if (userSex(sex) == MALE) {
            bmr = (weight * 10) + (height * 6.25) - (age * 5) + 5
            val finalTotal = bmr * userActivityLevel(level)
            finalTotal.toInt()

        } else {
            bmr = (weight * 10) + (height * 6.25) - (age * 5) - 161
            val finalTotal = bmr * userActivityLevel(level)
            finalTotal.toInt()
        }

    }

    private fun messageCaloriesNeeded(age: Int, weight: Int, height: Int, sex: Int): String {
        return " " + calculateCalories(
            age,
            weight,
            height,
            sex
        ) + " " + resources.getString(R.string.last_result_body_1)
    }

    private fun messageBmr(): String {
        return " "+ bmr.toInt() + " " + resources.getString(R.string.last_result_body_1)
    }
}

