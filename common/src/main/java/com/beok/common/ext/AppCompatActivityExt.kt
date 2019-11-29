package com.beok.common.ext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.goActivity(activityClass: Class<*>) {
    val intent = Intent(this, activityClass)
    startActivity(intent)
}