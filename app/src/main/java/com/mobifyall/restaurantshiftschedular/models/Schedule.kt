package com.mobifyall.restaurantshiftschedular.models

import android.graphics.Color
import java.util.*

data class Schedule(val startDate: Date, val endData: Date, val color: Int, val role: String)

data class Colors(val name: String, val hex: Int)

@JvmInline
value class Role(private val role: String){
    fun type() = role
}


object HelperData {
    val colorList: List<Colors> = mutableListOf<Colors>().apply {
        add(Colors("Red", Color.RED))
        add(Colors("Blue", Color.BLUE))
        add(Colors("Green", Color.GREEN))
    }

    val rolesList: List<Role> = mutableListOf<Role>().apply {
        add(Role("Waitress"))
        add(Role("Prep"))
        add(Role("Cook"))
        add(Role("Front of"))
    }

}