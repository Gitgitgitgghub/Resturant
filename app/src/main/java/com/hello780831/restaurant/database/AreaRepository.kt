package com.hello780831.restaurant.database

import com.hello780831.restaurant.model.Area

object AreaRepository {
    private val mDAO :AreaDAO = RestaurantDatabase.getInstance().getAreaDAO()

    suspend fun getAllArea() = mDAO.getAllArea()

    suspend fun insertArea(area: Area){
        mDAO.insertArea(area)
    }

    suspend fun getArea(area: String) = mDAO.getArea(area)
}