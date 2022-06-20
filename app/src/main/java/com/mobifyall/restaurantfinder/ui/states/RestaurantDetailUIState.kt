package com.mobifyall.restaurantfinder.ui.states

class RestaurantDetailUI(val data: RestaurantDetailUIState) : MainUIState()

data class RestaurantDetailUIState(
    val website: String
)