package com.mobifyall.restaurantfinder.core.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantDetails(
    @SerializedName("result") val result: Restaurant? = null,
) : Parcelable

@Parcelize
data class Restaurant(
    @SerializedName("formatted_address") val formattedAddress: String? = null,
    @SerializedName("vicinity") val vicinity: String? = null,
    @SerializedName("geometry") val geometry: Geometry? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("rating") val rating: Double? = null,
    @SerializedName("user_ratings_total") val ratingCount: Int? = null,
    @SerializedName("opening_hours") val openingHours: OpeningHours? = null,
    @SerializedName("icon") val icon: String? = null,
    @SerializedName("place_id") val placeID: String? = null,
    @SerializedName("website") val website: String? = null,
) : Parcelable {
    fun getAddress() = formattedAddress ?: vicinity
}