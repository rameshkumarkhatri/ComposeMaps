package com.mobifyall.restaurantfinder

import com.mobifyall.restaurantfinder.core.models.Response
import com.mobifyall.restaurantfinder.core.repos.RestaurantSearchRepo

class RestaurantSearchRepoFakeImp : RestaurantSearchRepo
{
    override suspend fun searchRestaurants(query: String): Response {
        return Response(listOf())
    }

    override suspend fun getNearByRestaurants(lat: Double, lng: Double): Response {
        return Response(listOf())
    }
}
