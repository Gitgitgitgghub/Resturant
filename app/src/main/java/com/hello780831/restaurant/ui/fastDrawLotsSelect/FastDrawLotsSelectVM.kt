package com.hello780831.restaurant.ui.fastDrawLotsSelect

import android.app.Application
import com.hello780831.restaurant.model.Area
import com.hello780831.restaurant.model.AreaRestaurants
import com.hello780831.restaurant.model.Restaurant
import com.hello780831.restaurant.ui.restaurantList.RestaurantListVM

class FastDrawLotsSelectVM(application: Application) : RestaurantListVM(application) {


    override fun drawLots(){
        val areaRestaurant = mAreaRestaurantLiveData.value
        if (areaRestaurant.isNullOrEmpty()) return
        val drawLotRestaurants = mutableListOf<Restaurant>()
        areaRestaurant.forEach {
            it.restaurant.forEach { restaurant ->
                if (restaurant.isCheck){
                    drawLotRestaurants.add(restaurant)
                }
            }
        }
        if (drawLotRestaurants.isNotEmpty()){
            mDrawLotRestaurantsLiveData.value = drawLotRestaurants
        }
    }

    override fun fetchRestaurantComplete(result: List<AreaRestaurants>) {
        val mergeData = mutableListOf<Restaurant>()
        val mergeAreaRestaurants = AreaRestaurants(Area(""),mergeData)
        result.forEach {
            mergeData.addAll(it.restaurant)
        }
        super.fetchRestaurantComplete(mutableListOf(mergeAreaRestaurants))
    }
}