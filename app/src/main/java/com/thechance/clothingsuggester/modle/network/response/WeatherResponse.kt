package com.thechance.clothingsuggester.modle.network.response

data class WeatherResponse(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)