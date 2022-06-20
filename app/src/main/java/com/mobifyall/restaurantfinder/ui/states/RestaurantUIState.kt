package com.mobifyall.restaurantfinder.ui.states

import com.mobifyall.restaurantfinder.extensions.emptyString

data class RestaurantUIState(
    val title: String,
    val desc: String,
    val isFav: Boolean,
    val imageUrl: String,
    val rating: Float,
    val ratingCount: String,
    val lat: Double?,
    val lng: Double?,
    val otherDesc: String = emptyString(),
    val id: String
)