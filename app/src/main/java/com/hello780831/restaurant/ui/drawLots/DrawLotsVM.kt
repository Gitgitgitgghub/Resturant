package com.hello780831.restaurant.ui.drawLots

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hello780831.restaurant.model.Restaurant

class DrawLotsVM(application: Application) : AndroidViewModel(application) {

    val mDrawLotsResultLiveData = MutableLiveData<Restaurant>()

    fun drawLot(restaurants: List<Restaurant>){
        val random = (restaurants.indices).random()
        mDrawLotsResultLiveData.value = restaurants[random]
    }
}