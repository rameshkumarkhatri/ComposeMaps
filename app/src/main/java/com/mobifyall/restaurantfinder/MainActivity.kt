package com.mobifyall.restaurantfinder

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mobifyall.restaurantfinder.ui.screens.MainListScreen
import com.mobifyall.restaurantfinder.ui.theme.RestaurantFinderComposeTheme
import com.mobifyall.restaurantfinder.viewmodels.RestaurantSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: RestaurantSearchViewModel by viewModels()
    private var fusedLocationClient: FusedLocationProviderClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RestaurantFinderComposeTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = navLandingDestination
                    ) {
                        composable(navLandingDestination) {
                            MainListScreen(
                                viewModel = viewModel,
                            )
                        }
                        composable(
                            navDescriptionDestination,
                            arguments = listOf(navArgument(argIndex) { type = NavType.IntType })
                        ) {
                        }
                    }

                }
            }
        }
        permissionFunctionality()
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                getLocationAndCallService()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                getLocationAndCallService()
            }
            else -> {
                // No location access granted.
                //todo show the location UI why we need this
            }
        }
    }


    private fun permissionFunctionality() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                getLocationAndCallService()

            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.
                //todo show the bottomsheet
            }
            else -> {
                // You can directly ask for the permission.
                locationPermissionRequest.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    private fun getLocationAndCallService() {

        val locationService = getSystemService(LOCATION_SERVICE) as LocationManager
        val isEnabled = LocationManagerCompat.isLocationEnabled(locationService)
        if (!isEnabled) {
            viewModel.showLocationError()
        } else {
            if (fusedLocationClient == null) {
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            }
            fusedLocationClient?.lastLocation?.addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        viewModel.getNearByRestaurant(location.latitude, location.longitude)
                    } else {
                        viewModel.showLocationError()
                        //todo fallback approach if it is null then to get location from the location manager
                    }

                }
//            val location = locationService.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//            if(location != null) {
//                viewModel.getNearByRestaurant(location.latitude, location.longitude)
//            }
//            else {
//                viewModel.showLocationError()
//                getCurrentLocation(locationService, LocationManager.GPS_PROVIDER, null, Executor {
//
//                }, Consumer {
//
//                })
//            }
        }
    }

}