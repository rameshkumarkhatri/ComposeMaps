package com.mobifyall.restaurantfinder.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.mobifyall.restaurantfinder.core.models.Location
import com.mobifyall.restaurantfinder.core.models.Response
import com.mobifyall.restaurantfinder.core.repos.RestaurantSearchRepo
import com.mobifyall.restaurantfinder.lists.FavoriteManger
import com.mobifyall.restaurantfinder.mapper.RestaurantUIMapper
import com.mobifyall.restaurantfinder.ui.states.MainUIState
import com.mobifyall.restaurantfinder.ui.states.RestaurantUIState
import com.mobifyall.restaurantfinder.ui.states.ViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RestaurantSearchViewModel @Inject constructor(
    private val repo: RestaurantSearchRepo,
    private val mapper: RestaurantUIMapper,
    private val favoriteManger: FavoriteManger
) :
    ViewModel() {
    //region private properties
    private val _uiState = MutableStateFlow<MainUIState>(MainUIState.Loading)
    private val _locationState = MutableStateFlow<LatLng>(LatLng(0.0, 0.0))
    private var restaurantsResponse: Response? = null
    private var currentViewType: ViewType = ViewType.ListView
    //endregion

    //region public properties
    val uiState: StateFlow<MainUIState> = _uiState
    val locationState: StateFlow<LatLng> = _locationState
    //endregion

    private var job: Job? = null
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, t ->
        t.printStackTrace()
        viewModelScope.launch {
            _uiState.emit(
                value = when (t) {
                    is IOException -> {
                        MainUIState.NoResult("Please check your internet connection")
                    }
                    is HttpException -> {
                        MainUIState.NoResult(
                            "There is some api error occurred!" +
                                    " please try later"
                        )
                    }
                    else -> {
                        MainUIState.NoResult("No result")
                    }
                }
            )
        }
    }


    fun getNearByRestaurant(lat: Double, lng: Double) {
        job = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _locationState.emit(LatLng(lat, lng))
            repo.getNearByRestaurants(lat, lng).let {
                restaurantsResponse = it
                val uiState = mapper.getRestaurantsUIState(it, ViewType.ListView)
                _uiState.emit(uiState)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun showLocationError() {
        viewModelScope.launch {
            _uiState.emit(
                MainUIState.Error(
                    "Please grant permission and enable location." +
                            "We need to access location to show the nearby restaurants.",
                    Icons.Default.LocationOn
                )
            )
        }
    }

    fun toggle(viewType: ViewType) {
        job = viewModelScope.launch(Dispatchers.Default + coroutineExceptionHandler) {
            restaurantsResponse?.let {
                currentViewType =
                    if (viewType == ViewType.ListView) ViewType.MapView else ViewType.ListView

                val uiState = mapper.getRestaurantsUIState(it, currentViewType)
                _uiState.emit(uiState)
            }
        }
    }

    fun favClicked(id: String) {
        job = viewModelScope.launch(Dispatchers.Default + coroutineExceptionHandler) {
            restaurantsResponse?.let {
                favoriteManger.addRemoveFav(id)
                //we can do better to optimize this
                val uiState = mapper.getRestaurantsUIState(it, currentViewType)
                _uiState.emit(uiState)
            }
        }
    }
}