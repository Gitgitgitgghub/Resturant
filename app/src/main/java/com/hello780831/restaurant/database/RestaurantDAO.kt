package com.hello780831.restaurant.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hello780831.restaurant.model.Area
import com.hello780831.restaurant.model.Restaurant

@Dao
interface RestaurantDAO {

    @Query("SELECT * FROM Restaurant")
    suspend fun getAllRestaurant() :List<Restaurant>

    @Query("SELECT * FROM Restaurant WHERE isCheck LIKE :isCheck")
    suspend fun getAllRestaurantByCheck(isCheck :Boolean) :List<Restaurant>

    @Query("SELECT * FROM Restaurant WHERE area LIKE :area")
    suspend fun getRestaurantsByArea(area : String) :List<Restaurant>

    @Insert
    suspend fun insertRestaurant(vararg restaurant: Restaurant)

    @Update
    suspend fun updateRestaurant(vararg restaurant: Restaurant)

    @Delete
    suspend fun deleteRestaurant(vararg restaurant: Restaurant)

}