package com.thechance.clothingsuggester.modle.network.response

data class WeatherResponse(
    val base: String,
    val clouds: CloudsResponse,
    val cod: Int,
    val coord: CoordResponse,
    val dt: Int,
    val id: Int,
    val main: MainResponse,
    val name: String,
    val sys: SysResponse,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: WindResponse
)