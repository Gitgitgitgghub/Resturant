package com.hello780831.restaurant.database

import androidx.lifecycle.LiveData
import com.hello780831.restaurant.model.Area
import com.hello780831.restaurant.model.Restaurant

object RestaurantRepository {

    private val mDAO :RestaurantDAO = RestaurantDatabase.getInstance().getRestaurantDAO()

    suspend fun getAllRestaurant() = mDAO.getAllRestaurant()

    suspend fun getRestaurantsByArea(area : Area) = mDAO.getRestaurantsByArea(area.areaName)

    suspend fun insertRestaurant(vararg restaurant: Restaurant){
        mDAO.insertRestaurant(*restaurant)
    }

    suspend fun updateRestaurant(vararg restaurant: Restaurant){
        mDAO.updateRestaurant(*restaurant)
    }

    suspend fun deleteCheckedRestaurant(){
        mDAO.getAllRestaurantByCheck(true).forEach {
            mDAO.deleteRestaurant(it)
        }
    }
}