package com.mobifyall.restaurantfinder.core.service

import com.mobifyall.restaurantfinder.BuildConfig
import com.mobifyall.restaurantfinder.core.models.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * not to use we can use through hilt dependency injection
 */
object RetrofitClient {
    val service = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RestaurantService::class.java)
}

interface RestaurantService {
    @GET("nearbysearch/json?inputtype=textquery")
    suspend fun searchRestaurants( @Query("input") query: String, apiKey: String): Response

    @GET("nearbysearch/json")
    suspend fun getNearByRestaurants(
        @Query("location") location: String, //by comma seperate lat,lng
        @Query("key") apiKey: String,
        @Query("type") type: String = TypeRestaurant,
        @Query("radius") radius: String = DefaultRadius,
    ): Response
}

const val TypeRestaurant = "restaurant"
const val DefaultRadius = "1500"