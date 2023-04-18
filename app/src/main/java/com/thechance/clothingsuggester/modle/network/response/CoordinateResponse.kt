package com.thechance.clothingsuggester.modle.network.response

import com.google.gson.annotations.SerializedName

data class CoordinateResponse(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
)