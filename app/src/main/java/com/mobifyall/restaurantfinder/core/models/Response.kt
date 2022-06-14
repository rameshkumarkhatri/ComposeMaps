package com.mobifyall.restaurantfinder.core.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Response(
    @SerializedName("candidates") val candidates: List<Candidate>? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("error_message") val errorMessage: String? = null,
) : Parcelable {

}

@Parcelize
data class Candidate(
    @SerializedName("formatted_address") val formattedAddress: String? = null,
    @SerializedName("geometry") val geometry: Geometry? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("rating") val rating: Double? = null,
    @SerializedName("opening_hours") val openingHours: OpeningHours? = null,
) : Parcelable

@Parcelize
data class Geometry(
    @SerializedName("location") val location: Location? = null,
    @SerializedName("geometry") val geometry: Geometry? = null,
) : Parcelable

@Parcelize
data class Location(
    @SerializedName("lat") val latitude: Double? = null,
    @SerializedName("lng") val longitude: Double? = null,
) : Parcelable

@Parcelize
data class OpeningHours(
    @SerializedName("open_now") val openNow: Boolean = false,
) : Parcelable