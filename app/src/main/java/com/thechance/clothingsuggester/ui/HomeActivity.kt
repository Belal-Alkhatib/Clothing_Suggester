package com.thechance.clothingsuggester.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.thechance.clothingsuggester.R
import com.thechance.clothingsuggester.modle.network.response.BaseResponse
import com.thechance.clothingsuggester.presenter.HomePresenter

class HomeActivity : AppCompatActivity() , IHomeView{

    private val presenter: HomePresenter = HomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.getWeather()

    }

    override fun showLoading() {
        Log.e(TAG, "showLoading------------")
    }

    override fun hideLoading() {
        Log.e(TAG, "hideLoading-------------")
    }

    override fun displayWeatherDetails(weather: BaseResponse) {
        Log.e(TAG, "displayWeatherDetails: $weather")

    }

    override fun onGetDataFailure(error: String) {
        Log.e(TAG, "onGetDataFailure: $error")
    }

    companion object{
        const val TAG = "bk"
    }
}