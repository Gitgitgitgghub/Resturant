package com.hello780831.restaurant.appDefine

import android.app.Application
import com.hello780831.restaurant.database.RestaurantDatabase

class RestaurantApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDatabase()
    }

    private fun initDatabase(){
        RestaurantDatabase.init(this)
    }
}