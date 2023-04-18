package com.thechance.clothingsuggester.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.thechance.clothingsuggester.R
import com.thechance.clothingsuggester.databinding.ActivityMainBinding
import com.thechance.clothingsuggester.modle.network.response.BaseResponse
import com.thechance.clothingsuggester.presenter.HomePresenter

class HomeActivity : AppCompatActivity() , IHomeView{

    private val presenter: HomePresenter = HomePresenter(this)
    private lateinit var progressDialog:ProgressDialog
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        Glide
            .with(this)
            .load("https://github.com/Belal-Alkhatib/TheChance_Docs/blob/main/weather_icon/10d.png?raw=true")
            .centerCrop()
            .into(binding.imageViewWeather);
        presenter.getWeather()

    }

    override fun showLoading() {
        Log.e(TAG, "showLoading------------")
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Uploading...")
        progressDialog.setMessage("Please wait while the data is being uploaded")
        progressDialog.show()
    }

    override fun hideLoading() {
        Log.e(TAG, "hideLoading-------------")
        progressDialog.dismiss()
    }

    override fun displayWeatherDetails(weather: BaseResponse) {
        Log.e(TAG, "displayWeatherDetails: $weather")

        runOnUiThread {}

    }

    override fun showFailureGetData(error: String) {
        Log.e(TAG, "onGetDataFailure: $error")
    }

    companion object{
        const val TAG = "bk"
    }
}

