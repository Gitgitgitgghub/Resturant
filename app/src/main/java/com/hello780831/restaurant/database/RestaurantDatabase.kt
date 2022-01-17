package com.hello780831.restaurant.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hello780831.restaurant.appDefine.DefaultArea
import com.hello780831.restaurant.appDefine.DefaultRestaurant
import com.hello780831.restaurant.model.Area
import com.hello780831.restaurant.model.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.NullPointerException

@Database(entities = [Restaurant::class, Area::class],version = 1)
abstract class RestaurantDatabase : RoomDatabase() {

    companion object{
        private var mInstance :RestaurantDatabase? = null

        @Synchronized
        fun init(context: Context) {
            if (mInstance == null){
                mInstance = Room
                    .databaseBuilder(context,RestaurantDatabase::class.java,RestaurantDatabase::class.java.simpleName)
                    .build()
            }
            mInstance!!.initDefaultData()
        }

        @Synchronized
        fun getInstance(): RestaurantDatabase {
            if (mInstance == null){
                throw NullPointerException("you should call function init first")
            }
            return mInstance!!
        }
    }

    abstract fun getRestaurantDAO() :RestaurantDAO

    abstract fun getAreaDAO() :AreaDAO

    fun initDefaultData(){
        insertDefaultArea()
        insertDefaultRestaurant()
    }

    private fun insertDefaultRestaurant(){
        GlobalScope.launch(Dispatchers.IO) {
            if (RestaurantRepository.getAllRestaurant().isEmpty()){
                DefaultRestaurant.values().forEach { restaurant ->
                    RestaurantRepository.insertRestaurant(restaurant.toRestaurant())
                }
            }
        }
    }

    private fun insertDefaultArea(){
        GlobalScope.launch(Dispatchers.IO) {
            if (AreaRepository.getAllArea().isEmpty()){
                DefaultArea.values().forEach { area ->
                    AreaRepository.insertArea(area.toArea())
                }
            }
        }
    }

}