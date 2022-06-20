package com.mobifyall.restaurantfinder

import com.mobifyall.restaurantfinder.core.models.*
import com.mobifyall.restaurantfinder.extensions.emptyString

object DummyData {
    val place = Candidate(
        formattedAddress = "898 rosemead center, Los Angeles, CA, 91998",
        vicinity = "898 rosemead center, Los Angeles, CA, 91998",
        rating = 3.9,
        icon = "https://i.picsum.photos/id/326/200/300.jpg?hmac=SKzjQ5ycCVyISiOfq2m-GqpQ5zWT_J202KPYG7z0uB4",
        name = "Restaurant - 1",
        ratingCount = 23,
        geometry = Geometry(
            Location(193.9, 23.9)
        ),
        openingHours = OpeningHours(true),
        placeID = "isdfqw4q0f"
    )
    val responseSuccess = Response(
        listOf(
            place
        ), "OK", emptyString(), null
    )
    val responseEmpty = Response(
        emptyList(), "OK", emptyString(), null
    )
    val responseError = Response(
        listOf(), "Error", emptyString(), null
    )
}