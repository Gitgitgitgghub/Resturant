package com.hello780831.restaurant.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hello780831.restaurant.model.Area

@Dao
interface AreaDAO {

    @Query("SELECT * FROM Area")
    suspend fun getAllArea() : List<Area>

    @Query("SELECT * FROM Area WHERE areaName LIKE :area")
    suspend fun getArea(area: String) : List<Area>

    @Insert
    suspend fun insertArea(area: Area)

}