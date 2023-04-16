package com.thechance.clothingsuggester.presenter

import com.thechance.clothingsuggester.modle.ApiServiceImpl
import com.thechance.clothingsuggester.modle.network.response.BaseResponse
import com.thechance.clothingsuggester.ui.IHomeView

class HomePresenter(private val view: IHomeView) {

    private val service = ApiServiceImpl()

    fun getWeather(){
        view.showLoading()
        service.getAllWeatherInfo(::onGetWeatherSuccess,::onFailure)
    }

    private fun onGetWeatherSuccess(response: BaseResponse) {
        response.let { view.displayWeatherDetails(response) }
        view.hideLoading()
    }
    private fun onFailure(message: String?) {
        view.onGetDataFailure(message.toString())
    }
}