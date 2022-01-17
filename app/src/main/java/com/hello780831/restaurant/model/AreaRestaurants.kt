package com.hello780831.restaurant.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class AreaRestaurants(
    var area: Area,
    var restaurant: List<Restaurant>
) :Parcelable
