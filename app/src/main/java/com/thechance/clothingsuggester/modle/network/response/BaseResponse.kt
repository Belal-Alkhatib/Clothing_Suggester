package com.thechance.clothingsuggester.modle.network.response

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: CloudsResponse,
    @SerializedName("cod")
    val responseCode: Int,
    @SerializedName("coord")
    val coordinate: CoordinateResponse,
    @SerializedName("dt")
    val dateTime: Int,
    @SerializedName("id")
    val cityId: Int,
    @SerializedName("main")
    val mainTemperature: MainTemperatureResponse,
    @SerializedName("name")
    val cityName: String,
    @SerializedName("sys")
    val locationInfo: LocationInfoResponse,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<WeatherResponse>,
    @SerializedName("wind")
    val wind: WindResponse
)