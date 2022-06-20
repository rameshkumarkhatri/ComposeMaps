package com.mobifyall.restaurantfinder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.mobifyall.restaurantfinder.ui.components.RestaurantItemComponentSmall
import com.mobifyall.restaurantfinder.ui.states.MainUIState
import com.mobifyall.restaurantfinder.viewmodels.RestaurantSearchViewModel


@Composable
fun MapsUI(
    modifier: Modifier,
    result: MainUIState.Success,
    viewModel: RestaurantSearchViewModel
) {
    var mapProperties by remember {
        mutableStateOf(
            MapProperties(maxZoomPreference = 20f, minZoomPreference = 10f)
        )
    }
    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(mapToolbarEnabled = false)
        )
    }
    val latLong = viewModel.locationState.collectAsState()
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLong.value, 11f)
    }
    Box(modifier = modifier) {
        GoogleMap(
            properties = mapProperties,
            uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState
        ) {
            result.list.forEach {
                MarkerInfoWindow(
                    state = MarkerState(position = LatLng(it.lat ?: 0.0, it.lng ?: 0.0)),
                    title = it.title,
                ) { _ ->
                    // Implement the custom info window here
                    Column(
                        Modifier
                            .width(300.dp)
                            .background(
                                Color.White
                            )
                    ) {
                        RestaurantItemComponentSmall(
                            uiState = it,
                            Modifier.background(color = Color.White)
                        ) {

                        }
                    }
                }
            }
        }
    }
}