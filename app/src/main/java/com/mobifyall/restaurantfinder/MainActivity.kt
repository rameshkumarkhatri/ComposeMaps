package com.mobifyall.restaurantfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobifyall.restaurantfinder.ui.screens.MainListScreen
import com.mobifyall.restaurantfinder.ui.theme.RestaurantFinderComposeTheme
import com.mobifyall.restaurantfinder.viewmodels.RestaurantSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: RestaurantSearchViewModel by viewModels()

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
//                            {
                                // we can also use the single event live data to navigate too
//                                navController.navigate(createUriDescription(it))
//                            }
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
    }
}