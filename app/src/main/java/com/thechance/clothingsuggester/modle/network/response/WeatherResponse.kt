package com.thechance.clothingsuggester.modle.network.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("description")
    val weatherDescription: String,
    @SerializedName("icon")
    val weatherIcon: String,
    @SerializedName("id")
    val weatherId: Int,
    @SerializedName("main")
    val weatherMain: String
)