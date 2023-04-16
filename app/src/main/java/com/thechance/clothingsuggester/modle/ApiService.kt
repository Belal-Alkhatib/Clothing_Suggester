package com.thechance.clothingsuggester.modle

import com.thechance.clothingsuggester.modle.network.response.BaseResponse


interface ApiService {
    fun getAllWeatherInfo(
        onSuccess: (response: BaseResponse) -> Unit,
        onFailure: (message: String?) -> Unit,
    )



}