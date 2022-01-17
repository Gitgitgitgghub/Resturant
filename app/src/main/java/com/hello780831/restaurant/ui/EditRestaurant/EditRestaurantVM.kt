package com.hello780831.restaurant.ui.EditRestaurant

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hello780831.restaurant.R
import com.hello780831.restaurant.appDefine.RestaurantApp
import com.hello780831.restaurant.database.AreaRepository
import com.hello780831.restaurant.database.RestaurantRepository
import com.hello780831.restaurant.model.Area
import com.hello780831.restaurant.model.Restaurant
import kotlinx.coroutines.launch

class EditRestaurantVM(application: Application) : AndroidViewModel(application) {

    private var mRestaurantInputData :Restaurant? = null
    val mInputErrorMessageLiveData = MutableLiveData<String>()

    fun setRestaurant(restaurant: Restaurant?){
        mRestaurantInputData = restaurant ?: Restaurant()
    }

    fun onInputChange(title :String,text :String){
        val application = getApplication<RestaurantApp>()
        if (mRestaurantInputData != null){
            when(title){
                application.getString(R.string.restaurantArea) -> mRestaurantInputData!!.area = text
                application.getString(R.string.restaurantName) -> mRestaurantInputData!!.restaurantName = text
                application.getString(R.string.restaurantAddress) -> mRestaurantInputData!!.address = text
                application.getString(R.string.restaurantPhone) -> mRestaurantInputData!!.phone = text
                application.getString(R.string.googleRatio) -> {
                    mRestaurantInputData!!.googleRatio = if (text.isEmpty()) 0f else text.toFloat()
                }
                application.getString(R.string.restaurantPrice) -> mRestaurantInputData!!.price = text
                application.getString(R.string.hasPark) -> {
                    mRestaurantInputData!!.hasPark = application.resources.getStringArray(R.array.hasParkOptionArray).indexOf(text) == 1
                }
                application.getString(R.string.restaurantMapLink) -> mRestaurantInputData!!.mapLink = text
            }
        }
    }

    fun addNewRestaurant(){
        if (mRestaurantInputData == null) return
        if (isInputDataValid())
        {
            viewModelScope.launch {
                if (AreaRepository.getArea(mRestaurantInputData!!.area).isNullOrEmpty()){
                    AreaRepository.insertArea(Area(mRestaurantInputData!!.area))
                }
                RestaurantRepository.insertRestaurant(mRestaurantInputData!!)
            }
        }else{
            mInputErrorMessageLiveData.value = getApplication<RestaurantApp>().getString(R.string.inputDataError)
        }
    }

    fun updateRestaurant(){
        viewModelScope.launch {
            RestaurantRepository.updateRestaurant(mRestaurantInputData!!)
        }
    }

    private fun isInputDataValid() :Boolean{
        return mRestaurantInputData != null &&
                mRestaurantInputData!!.area.isNotEmpty() &&
                mRestaurantInputData!!.restaurantName.isNotEmpty()
    }
}