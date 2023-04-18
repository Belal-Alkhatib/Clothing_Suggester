package com.thechance.clothingsuggester.modle.network.response

import com.google.gson.annotations.SerializedName

data class WindResponse(
    @SerializedName("")
    val windDirection: Int,
    @SerializedName("")
    val windGust: Double,
    @SerializedName("")
    val windSpeed: Double
)