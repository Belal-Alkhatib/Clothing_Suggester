package com.thechance.clothingsuggester.ui

import com.thechance.clothingsuggester.modle.network.response.BaseResponse

interface IHomeView {
    fun showLoading()
    fun hideLoading()

    fun displayWeatherDetails(weather: BaseResponse)

    fun showFailureGetData(error: String)

}