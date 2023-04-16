package com.thechance.clothingsuggester.modle

import com.thechance.clothingsuggester.modle.network.response.WeatherResponse


interface ApiService {
    fun getAllWeatherInfo(
        onSuccess: (response: WeatherResponse) -> Unit,
        onFailure: (message: String?) -> Unit,
    )



}