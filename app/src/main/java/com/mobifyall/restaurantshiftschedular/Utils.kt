package com.mobifyall.restaurantshiftschedular

import android.text.format.DateUtils
import android.view.View


enum class Gender(val gender: String) {
    MALE("male"),
    FEMALE("female")
}



fun String.getDateTimeSeparately(): Array<String> {
    return arrayOf("", "")
}


fun View.hide() {
    this.visibility = View.GONE
}

