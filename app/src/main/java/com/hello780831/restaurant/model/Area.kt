package com.hello780831.restaurant.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Keep
@Entity(tableName = "Area")
@Parcelize
data class Area(
    var areaName :String,
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0
) :Parcelable
