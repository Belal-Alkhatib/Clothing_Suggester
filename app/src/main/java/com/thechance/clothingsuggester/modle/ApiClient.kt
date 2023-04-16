package com.thechance.clothingsuggester.modle


import com.thechance.clothingsuggester.BuildConfig
import com.thechance.clothingsuggester.modle.network.util.MethodRequest
import okhttp3.*

class ApiClient {


    private val okHttpClient = OkHttpClient().newBuilder()
        .build()


    private fun request(
        method: MethodRequest = MethodRequest.GET,
        path: String,
        body: RequestBody? = null
    ): Request {
        return Request.Builder()
            .url(getHttpUrl(path))
            .method(method.name, body)
            .build()
    }

    fun getRequest(
        path: String,
    ): Call {
        val request = request(path = path)
        return executeHttpRequest(request)
    }


    private fun getHttpUrl(path: String): HttpUrl {
        return HttpUrl.Builder()
            .scheme(HTTPS)
            .host(BuildConfig.HOST)
            .addPathSegments(path)
            .addQueryParameter(Constants.ApiParameterNames.CITY_NAME, CITY_NAME)
            .addQueryParameter(Constants.ApiParameterNames.UNITS, UNITS)
            .addQueryParameter(Constants.ApiParameterNames.API_KEY, BuildConfig.API_KEY)
            .build()
    }

    private fun executeHttpRequest(
        request: Request
    ): Call {
        return okHttpClient.newCall(request)
    }

    private companion object {
        const val HTTPS = "https"
        const val CITY_NAME = "Gaza"
        const val UNITS = "metric"
    }

}