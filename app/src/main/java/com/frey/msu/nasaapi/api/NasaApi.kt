package com.frey.msu.nasaapi.api

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "BbQz4fV2nYOPlzH3DdNBVQM19agOYMukqPgioKX9"

interface NasaApi {
    @GET("planetary/apod")
    suspend fun fetchApod(
        @Query("api_key") apiKey: String = "DEMO_KEY",
        @Query("count") count: Int = 40
    ): List<ApodResponse>
}