package com.mobifyall.restaurantfinder

import com.mobifyall.restaurantfinder.core.models.Response
import com.mobifyall.restaurantfinder.core.repos.RestaurantSearchRepo

class RestaurantSearchRepoFakeImp(var returnError: Boolean, var size: Int = 0) :
    RestaurantSearchRepo {
    override suspend fun searchRestaurants(query: String): Response {
        return if (returnError) DummyData.responseError else {
            if (size == 0) DummyData.responseEmpty
            else DummyData.responseSuccess
        }
    }

    override suspend fun getNearByRestaurants(lat: Double, lng: Double): Response {
        return if (returnError) DummyData.responseError else {
            if (size == 0) DummyData.responseEmpty
            else DummyData.responseSuccess
        }
    }
}
