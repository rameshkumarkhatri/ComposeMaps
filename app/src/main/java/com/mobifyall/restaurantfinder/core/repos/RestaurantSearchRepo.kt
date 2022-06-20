package com.mobifyall.restaurantfinder.core.repos

import com.mobifyall.restaurantfinder.BuildConfig
import com.mobifyall.restaurantfinder.core.models.Response
import com.mobifyall.restaurantfinder.core.models.RestaurantDetails
import com.mobifyall.restaurantfinder.core.service.RestaurantService

interface RestaurantSearchRepo {
    suspend fun searchRestaurants(query: String): Response
    suspend fun getNearByRestaurants(lat: Double, lng: Double): Response
    suspend fun getRestaurantDetails(id: String): RestaurantDetails
}

class RestaurantSearchRepoImp(private val service: RestaurantService) : RestaurantSearchRepo {
    override suspend fun searchRestaurants(query: String): Response {
        return service.searchRestaurants(query, BuildConfig.googleApiKey)
    }

    override suspend fun getNearByRestaurants(lat: Double, lng: Double): Response {
        return service.getNearByRestaurants("$lat,$lng", BuildConfig.googleApiKey)
    }

    override suspend fun getRestaurantDetails(id: String): RestaurantDetails {
        return service.getRestaurantDetails(id, BuildConfig.googleApiKey)
    }
}