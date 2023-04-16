package com.thechance.clothingsuggester.modle

import com.google.gson.Gson
import com.thechance.clothingsuggester.modle.network.response.WeatherResponse
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


class ApiServiceImpl : ApiService {

    private val client = ApiClient()
    override fun getAllWeatherInfo(
        onSuccess: (response: WeatherResponse) -> Unit,
        onFailure: ( message: String?) -> Unit
    ) {
        client.getRequest(ApiEndPoint.version).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val result = Gson().fromJson(jsonString, WeatherResponse::class.java)
                    onSuccess(result)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                onFailure(e.message)
            }

        })
    }

}