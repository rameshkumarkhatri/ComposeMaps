package com.mobifyall.restaurantfinder.di

import com.google.gson.GsonBuilder
import com.mobifyall.restaurantfinder.BuildConfig
import com.mobifyall.restaurantfinder.core.service.RestaurantService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRestaurantService(): RestaurantService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                GsonBuilder().create()
            ))
            .build().create(RestaurantService::class.java)
    }
}