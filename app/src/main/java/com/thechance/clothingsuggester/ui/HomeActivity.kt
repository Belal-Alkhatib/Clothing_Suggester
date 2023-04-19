package com.thechance.clothingsuggester.ui

import android.app.ProgressDialog
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.thechance.clothingsuggester.R
import com.thechance.clothingsuggester.databinding.ActivityHomeBinding
import com.thechance.clothingsuggester.modle.network.response.*
import com.thechance.clothingsuggester.presenter.HomePresenter
import com.thechance.clothingsuggester.ui.util.Constants
import kotlin.random.Random

class HomeActivity : AppCompatActivity(), IHomeView {

    private val presenter: HomePresenter = HomePresenter(this)
    private lateinit var progressDialog:ProgressDialog

    lateinit var sharedPref: SharedPreferences
    lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.getWeather()
        sharedPref = getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, MODE_PRIVATE)

    }


    private fun updateWeatherView(weather: BaseResponse){
        Log.e(TAG, " ****************** updateWeatherView ${weather.dateTime.formatDate()}")


        binding.apply {
            layoutWeatherInfo.textViewDate.text = weather.dateTime.formatDate()
            textViewCityNameLocation.text = weather.cityName
            layoutWeatherInfo.textViewCountryName.text = weather.cityName
        }
        updateMainWeatherInfo(weather.mainTemperature)
        updateLocationInfo(weather.locationInfo)
        updateWeatherInfoViews(weather.weathers)
        updateWindWeatherInfo(weather.wind)
    }


    private fun updateMainWeatherInfo(main: MainTemperatureResponse){
        binding.apply {
            layoutWeatherInfo.textViewWeatherDegree.text = main.temperature.toInt().toString().degreeFormat()
            layoutWeatherInfo.textViewFeelsLike.text = "Feels Like ${main.feelsLike}"
            include.textViewPressure.text = main.pressure.toString()
            include.textViewHumidity.text = main.humidity.toString()
            buttonGetWear.setOnClickListener {
                showAppropriateClothing(main.temperature)
            }
        }

    }


    private fun updateLocationInfo(location: LocationInfoResponse){
        Log.e(TAG, " ****************** updateLocationInfo ${location.sunrise.formatTime()}")
        binding.apply {
            layoutWeatherInfo.textViewCountryName.text = location.country
            include.textViewSunrise.text = location.sunrise.formatTime()
            include.textViewSunset.text = location.sunset.formatTime()
        }
    }


    val weatherIconMap = mapOf(
        Constants.WeatherIconsConstants.CLEAR_SCY to
                Constants.WeatherIconsConstants.CLEAR_SCY_URL,
        Constants.WeatherIconsConstants.FEW_CLOUDS to
                Constants.WeatherIconsConstants.FEW_CLOUDS_URL,
        Constants.WeatherIconsConstants.SCATTERED_CLOUDS to
                Constants.WeatherIconsConstants.SCATTERED_CLOUDS_URL,
        Constants.WeatherIconsConstants.BROKEN_CLOUDS to
                Constants.WeatherIconsConstants.BROKEN_CLOUDS_URL,
        Constants.WeatherIconsConstants.SHOWER_RAIN to
                Constants.WeatherIconsConstants.SHOWER_RAIN_URL,
        Constants.WeatherIconsConstants.RAIN to
                Constants.WeatherIconsConstants.RAIN_URL,
        Constants.WeatherIconsConstants.THUNDERSTORM to
                Constants.WeatherIconsConstants.THUNDERSTORM_URL,
        Constants.WeatherIconsConstants.SNOW to
                Constants.WeatherIconsConstants.SNOW_URL,
        Constants.WeatherIconsConstants.MIST to
                Constants.WeatherIconsConstants.MIST_URL,

        Constants.WeatherIconsConstants.CLEAR_SCY_Night to
                Constants.WeatherIconsConstants.CLEAR_SCY_NIGHT_URL,
        Constants.WeatherIconsConstants.FEW_CLOUDS_NIGHT to
                Constants.WeatherIconsConstants.FEW_CLOUDS_NIGHT_URL,
        Constants.WeatherIconsConstants.SCATTERED_CLOUDS_NIGHT to
                Constants.WeatherIconsConstants.SCATTERED_CLOUDS_NIGHT_URL,
        Constants.WeatherIconsConstants.BROKEN_CLOUDS_NIGHT to
                Constants.WeatherIconsConstants.BROKEN_CLOUDS_NIGHT_URL,
        Constants.WeatherIconsConstants.SHOWER_RAIN_NIGHT to
                Constants.WeatherIconsConstants.SHOWER_RAIN_NIGHT_URL,
        Constants.WeatherIconsConstants.RAIN_NIGHT to
                Constants.WeatherIconsConstants.RAIN_NIGHT_URL,
        Constants.WeatherIconsConstants.THUNDERSTORM_NIGHT to
                Constants.WeatherIconsConstants.THUNDERSTORM_NIGHT_URL,
        Constants.WeatherIconsConstants.SNOW_NIGHT to
                Constants.WeatherIconsConstants.SNOW_NIGHT_URL,
        Constants.WeatherIconsConstants.MIST_NIGHT to
                Constants.WeatherIconsConstants.MIST_NIGHT_URL,
    )
    private fun updateWeatherInfoViews(weathers: List<WeatherResponse>){
        val currentWeather = weathers[0]
        currentWeather.apply {
            binding.apply {
                include.textViewClouds.text = weatherDescription
                layoutWeatherInfo.imageViewWeather.also {
                    it.loadImageFromNetwork(this@HomeActivity, weatherIconMap[weatherIcon], it)
                }
            }
        }
    }

    private fun updateWindWeatherInfo(wind: WindResponse){
        Log.e(TAG, " ****************** updateWindWeatherInfo ${wind.windSpeed.toString().speedFormat()}")

        binding.include.textViewWind.text = wind.windSpeed.toString().speedFormat()
    }


    private val coldClothes = listOf(R.drawable.cold_coat1, R.drawable.cold_coat2, R.drawable.cold_coat3)
    private val mildClothes = listOf(R.drawable.mild_tshirt1, R.drawable.mlid_tshirt2, R.drawable.mlid_tshirt3)
    private val warmClothes = listOf(R.drawable.warm_tshirt1, R.drawable.warm_tshirt2, R.drawable.warm_tshirt3)
    fun showAppropriateClothing(temperature: Double) {
        val clothes = getClothesForTemperature(temperature)

        val randomIndex = Random.nextInt(clothes.size)
        val choiceCloth = clothes[randomIndex]

        if (getLastUsedCloth() != choiceCloth) {
            saveLastUsedCloth(choiceCloth)
            binding.imageViewClouth.setImageResource(choiceCloth)
        } else {
            showAppropriateClothing(temperature)
        }
    }
    private fun getClothesForTemperature(temperature: Double): List<Int> {
        return when {
            temperature < 15 -> coldClothes
            temperature in 15.0..23.0 -> mildClothes
            else -> warmClothes
        }
    }
    fun saveLastUsedCloth(cloth: Int) {
        with(sharedPref.edit()) {
            putInt(Constants.USED_CLOTHES, cloth)
            apply()
        }
    }
    fun getLastUsedCloth(): Int {
        return sharedPref.getInt(
            Constants.USED_CLOTHES,
            R.drawable.suggest_wear_default_image
        )
    }



    override fun showLoading() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Uploading...")
        progressDialog.setMessage("Please wait while the data is being uploaded")
        progressDialog.show()
    }
    override fun hideLoading() {
        Log.e(HomeActivity.TAG, "hideLoading-------------")
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    override fun displayWeatherDetails(weather: BaseResponse) {
        runOnUiThread {
            updateWeatherView(weather)
        }
    }

    override fun showFailureGetData(error: String) {
        Log.e(HomeActivity.TAG, "onGetDataFailure: $error")
    }




    companion object{
        const val TAG = "bk"
    }

}