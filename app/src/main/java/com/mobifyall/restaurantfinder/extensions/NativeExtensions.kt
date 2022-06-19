package com.mobifyall.restaurantfinder.extensions


fun Int?.withBracketsOrEmpty(): String {
    return this?.let { "($it)" } ?: emptyString()
}

fun emptyString() = ""

fun String?.orDefaultImage(): String {
    return this ?: "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/cafe-71.png"
}

fun Float?.orZero() = this ?: 0.0f