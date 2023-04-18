package com.thechance.clothingsuggester.modle.network.response

import com.google.gson.annotations.SerializedName

data class LocationInfoResponse(
    @SerializedName("country")
    val country: String,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
)