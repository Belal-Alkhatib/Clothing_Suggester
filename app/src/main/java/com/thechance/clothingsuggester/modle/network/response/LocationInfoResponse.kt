package com.thechance.clothingsuggester.modle.network.response

data class LocationInfoResponse(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)