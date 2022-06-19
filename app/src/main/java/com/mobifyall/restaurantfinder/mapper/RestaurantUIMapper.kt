package com.mobifyall.restaurantfinder.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import com.mobifyall.restaurantfinder.core.models.Candidate
import com.mobifyall.restaurantfinder.core.models.Response
import com.mobifyall.restaurantfinder.extensions.orDefaultImage
import com.mobifyall.restaurantfinder.extensions.orZero
import com.mobifyall.restaurantfinder.extensions.withBracketsOrEmpty
import com.mobifyall.restaurantfinder.lists.FavoriteManger
import com.mobifyall.restaurantfinder.ui.states.MainUIState
import com.mobifyall.restaurantfinder.ui.states.RestaurantUIState
import com.mobifyall.restaurantfinder.ui.states.ViewType
import javax.inject.Inject

class RestaurantUIMapper @Inject constructor(private val manager: FavoriteManger){
    fun getRestaurantsUIState(data: Response, viewType: ViewType) : MainUIState {
        return when (data.status) {
            STATUS_OK -> {
                if(data.candidates?.isNotEmpty() == true) {
                    val list = createUIStates(data.candidates)
                    MainUIState.Success(
                        list,
                        viewType
                    )
                }else {
                    MainUIState.NoResult("There are no result found")
                }
            }
            else -> {
                MainUIState.Error(data.errorMessage.orEmpty(), Icons.Default.Person)
            }
        }
    }

    private fun createUIStates(list: List<Candidate>): List<RestaurantUIState> {
        return list.map {
            RestaurantUIState(
                title = it.name.toString(),
                desc = it.getAddress().toString(),
                rating = it.rating?.toFloat().orZero(),
                ratingCount = it.ratingCount.withBracketsOrEmpty(),
                isFav = manager.isFavorite(),
                imageUrl = it.icon.orDefaultImage(),
                lat = it.geometry?.location?.latitude,
                lng = it.geometry?.location?.longitude,
            )
        }
    }


        private val STATUS_OK = "OK"
}