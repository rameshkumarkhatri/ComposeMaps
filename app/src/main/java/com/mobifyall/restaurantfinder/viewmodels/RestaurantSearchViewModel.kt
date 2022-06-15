package com.mobifyall.restaurantfinder.viewmodels

import androidx.lifecycle.ViewModel
import com.mobifyall.restaurantfinder.core.repos.RestaurantSearchRepo
import com.mobifyall.restaurantfinder.ui.states.MainUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RestaurantSearchViewModel @Inject constructor(private val repo: RestaurantSearchRepo) :
    ViewModel() {
    //region private properties
    private val _uiState = MutableStateFlow<MainUIState>(MainUIState.Loading)
    //endregion

    //region public properties
    val uiState: StateFlow<MainUIState> = _uiState
    //endregion

    private var job: Job? = null
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, t ->
        t.printStackTrace()
        when (t) {
            is IOException -> {
                _uiState.tryEmit(
                    MainUIState.NoResult("Please check your internet connection")
                )
            }
            is HttpException -> {
//                uiStateLanding.value = LandingUIState.NoResult(
//                    "There is some api error occurred!" +
//                            "n please try later"
//                )
            }
            else -> {
//                uiStateLanding.value = LandingUIState.NoResult("No result")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}