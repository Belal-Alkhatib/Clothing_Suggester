package com.thechance.clothingsuggester.modle.network.response

import com.google.gson.annotations.SerializedName

data class MainTemperatureResponse(
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("grnd_level")
    val groundLevelPressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("sea_level")
    val seaLevelPressure: Int,
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("temp_max")
    val maxTemperature: Double,
    @SerializedName("temp_min")
    val minTemperature: Double
)