package com.mobifyall.restaurantfinder.ui.states

import android.graphics.drawable.Icon

sealed class MainUIState {
    object Loading: MainUIState()
    class NoResult(val message: String): MainUIState()
    class Error(val message: String, val icon: Icon): MainUIState()
    class Success(val list: List<RestaurantUIState>): MainUIState()
}