package com.frey.msu.nasaapi.api

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApodResponse(
    val title: String,
    val date: String,
    val url: String
)