package com.khalidcoding.caloriescalculator.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.khalidcoding.caloriescalculator.R


const val NO_ACTIVITY_CHOOSED = 0.1f
const val MALE_R_BTN = 2131362058
const val FEMALE = "female"
const val MALE = "male"

// Mifflin-st jeor formula
fun userActivityLevel(position: Int): Float {
    when (position) {
        1 -> return 1.2f
        2 -> return 1.375f
        3 -> return 1.55f
        4 -> return 1.725f
        5 -> return 1.9f
    }
    return NO_ACTIVITY_CHOOSED
}


fun userSex(sex: Int): String {
    return if (sex == MALE_R_BTN) {
        MALE
    } else
        FEMALE
}


fun showDialogMsg( caloriesNeeded: String,msgBmr: String, activity: Activity) {
    val dialog = Dialog(activity)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setContentView(R.layout.dialog_msg_layout)
    val bmr = dialog.findViewById(R.id.tv_body_1) as TextView
    bmr.text = msgBmr
    val calNeeded = dialog.findViewById(R.id.tv_body_2) as TextView
    calNeeded.text = caloriesNeeded
    val dismiss = dialog.findViewById(R.id.btn_close_dialog) as Button
    dismiss.setOnClickListener {
        dialog.dismiss()
    }
    dialog.show()

}