package com.mobifyall.restaurantfinder.lists

import javax.inject.Inject


class FavoriteManger @Inject constructor() {
    // we can change to map or other data structure, right now just to make it work
    // As of fact retrieval and checking is high complexity
    private val favList: MutableList<String> = mutableListOf()

    fun isFavorite(id: String): Boolean {
        return favList.indexOf(id) >= 0
    }

    private fun addToFavorite(id: String) {
        favList.add(id)
    }

    private fun removeFromFavorite(id: String) {
        favList.remove(id)
    }

    fun addRemoveFav(id: String) {
        if (id.isNullOrBlank()) return
        if (isFavorite(id)) {
            removeFromFavorite(id)
        } else {
            addToFavorite(id)
        }
    }
}