package com.hello780831.restaurant.ui.restaurantList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hello780831.restaurant.database.AreaRepository
import com.hello780831.restaurant.database.RestaurantRepository
import com.hello780831.restaurant.model.AreaRestaurants
import com.hello780831.restaurant.model.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

open class RestaurantListVM(application: Application) : AndroidViewModel(application) {

    val mAreaRestaurantLiveData = MutableLiveData<List<AreaRestaurants>>()
    val mDrawLotRestaurantsLiveData = MutableLiveData<List<Restaurant>>()
    private var mAllCheck = false

    fun fetchRestaurant(){
        viewModelScope.launch {
            val areaRestaurantList = mutableListOf<AreaRestaurants>()
            val areas = async(Dispatchers.IO) {
                AreaRepository.getAllArea()
            }
            areas.await().forEach {
                val restaurants = async(Dispatchers.IO) {
                    RestaurantRepository.getRestaurantsByArea(it)
                }
                areaRestaurantList.add(AreaRestaurants(it,restaurants.await()))
            }
            fetchRestaurantComplete(areaRestaurantList)
        }
    }

    open fun fetchRestaurantComplete(result :List<AreaRestaurants>){
        mAreaRestaurantLiveData.value = result
    }

    open fun drawLots(){
        val areaRestaurant = mAreaRestaurantLiveData.value
        if (areaRestaurant.isNullOrEmpty()) return
        val drawLotRestaurants = mutableListOf<Restaurant>()
        areaRestaurant.forEach {
            it.restaurant.forEach { restaurant ->
                drawLotRestaurants.add(restaurant)
            }
        }
        if (drawLotRestaurants.isNotEmpty()){
            mDrawLotRestaurantsLiveData.value = drawLotRestaurants
        }
    }

    fun restaurantCheckChange(isCheck: Boolean, restaurant: Restaurant){
        viewModelScope.launch {
            RestaurantRepository.updateRestaurant(restaurant)
        }
    }

    fun clickAllSelect(){
        mAreaRestaurantLiveData.value?.let {value ->
            val restaurants = mutableListOf<Restaurant>()
            viewModelScope.launch {
                mAllCheck = !mAllCheck
                value.forEach {
                    it.restaurant.forEach {restaurant ->
                        restaurant.isCheck = mAllCheck
                        restaurants.add(restaurant)
                    }
                }
                RestaurantRepository.updateRestaurant(*restaurants.toTypedArray())
                fetchRestaurant()
            }
        }
    }
}