package com.hello780831.restaurant.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hello780831.restaurant.R
import com.hello780831.restaurant.ui.fastDrawLotsSelect.FastDrawLotsSelectActivity

class AboutMeActivity : AppCompatActivity() {

    companion object{

        fun launch(context: Context){
            context.startActivity(Intent(context, AboutMeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)
    }
}