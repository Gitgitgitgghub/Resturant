package com.hello780831.restaurant.appDefine

import com.hello780831.restaurant.model.Area

enum class DefaultArea(private val area :String) {
    A("宜蘭市"),
    B("礁溪鄉");

    fun toArea() = Area(area)
}