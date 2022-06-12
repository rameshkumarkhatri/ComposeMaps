package com.mobifyall.restaurantfinder

const val argIndex = "index"
const val navLandingDestination = "landing"

const val navDescriptionDestination = "description/{$argIndex}"

fun createUriDescription(index: Int): String = "description/$index"