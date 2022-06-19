package com.mobifyall.restaurantfinder.di

import com.mobifyall.restaurantfinder.lists.FavoriteManger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FavoriteManagerModule {
    @Singleton
    @Provides
    fun provideFavoriteManager(): FavoriteManger {
        return FavoriteManger()
    }
}