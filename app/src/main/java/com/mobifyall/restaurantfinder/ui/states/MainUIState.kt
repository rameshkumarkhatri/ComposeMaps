package com.mobifyall.restaurantfinder.ui.states

import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainUIState {
    object Loading: MainUIState()
    class NoResult(val message: String): MainUIState()
    class Error(val message: String, val icon: ImageVector): MainUIState()
    class Success(val list: List<RestaurantUIState>, val viewType: ViewType): MainUIState()
}

sealed class ViewType(val text: String) {
    object ListView: ViewType(text = "View As Map")
    object MapView: ViewType(text = "View As List")
}

