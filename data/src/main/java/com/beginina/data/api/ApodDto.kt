package com.beginina.data.api

import com.google.gson.annotations.SerializedName

data class ApodDto(
    val title: String,
    val date: String,
    val url: String,
    val hdurl: String?,
    @SerializedName("media_type")
    val mediaType: String,
    val explanation: String
)
