package com.mobifyall.restaurantfinder.core.service

import com.mobifyall.restaurantfinder.core.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantService {
    @GET("nearbysearch/json?inputtype=textquery")
    suspend fun searchRestaurants(@Query("input") query: String, apiKey: String): Response

    @GET("nearbysearch/json")
    suspend fun getNearByRestaurants(
        @Query("location") location: String, //by comma separate lat,lng
        @Query("key") apiKey: String,
        @Query("type") type: String = TypeRestaurant,
        @Query("radius") radius: String = DefaultRadius,
    ): Response
}

const val TypeRestaurant = "restaurant"
const val DefaultRadius = "3500" //this is meter