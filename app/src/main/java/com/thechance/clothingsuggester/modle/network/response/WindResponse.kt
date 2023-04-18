package com.thechance.clothingsuggester.modle.network.response

import com.google.gson.annotations.SerializedName

data class WindResponse(
    @SerializedName("deg")
    val windDirection: Int,
    @SerializedName("gust")
    val windGust: Double,
    @SerializedName("speed")
    val windSpeed: Double
)