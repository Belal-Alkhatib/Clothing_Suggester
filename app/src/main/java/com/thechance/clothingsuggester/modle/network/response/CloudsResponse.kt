package com.thechance.clothingsuggester.modle.network.response

import com.google.gson.annotations.SerializedName

data class CloudsResponse(
    @SerializedName("all")
    val all: Int
)