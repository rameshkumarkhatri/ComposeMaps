package com.mobifyall.restaurantfinder.di

import com.mobifyall.restaurantfinder.core.repos.RestaurantSearchRepo
import com.mobifyall.restaurantfinder.core.repos.RestaurantSearchRepoImp
import com.mobifyall.restaurantfinder.core.service.RestaurantService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRestaurantSearchRepo(service: RestaurantService): RestaurantSearchRepo {
        return RestaurantSearchRepoImp(service)
    }
}