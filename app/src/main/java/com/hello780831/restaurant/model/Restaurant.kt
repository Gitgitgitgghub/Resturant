package com.hello780831.restaurant.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Keep
@Entity(tableName = "Restaurant")
@Parcelize
data class Restaurant(

    var area :String = "",
    var restaurantName :String = "",
    var phone :String = "",
    var address: String = "",
    var googleRatio :Float = 0.0f,
    var hasPark :Boolean = false,
    var price :String = "低",
    var mapLink :String = "",
    var isCheck :Boolean = true,
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0
) :Parcelable



