package com.frey.msu.nasaapi

import com.frey.msu.nasaapi.api.NasaApi
import com.frey.msu.nasaapi.api.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
// import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class PhotoRepository {
    private val nasaApi: NasaApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        nasaApi = retrofit.create<NasaApi>()
    }

    suspend fun fetchApod(count: Int = 50): List<GalleryItem> {
        val items = mutableListOf<GalleryItem>()

        // Determine the number of items to fetch in each request
        val maxItemsPerRequest = 40
        val fullRequestsCount = count / maxItemsPerRequest
        val remainingItems = count % maxItemsPerRequest

        // Fetch full requests
        repeat(fullRequestsCount) { _ ->
            val apodResponses = nasaApi.fetchApod(count = maxItemsPerRequest)
            items.addAll(apodResponses.map { apodResponse ->
                GalleryItem(
                    title = apodResponse.title,
                    id = apodResponse.date,
                    url = apodResponse.url
                )
            })
        }

        // Fetch remaining items
        if (remainingItems > 0) {
            val apodResponses = nasaApi.fetchApod(count = remainingItems)
            items.addAll(apodResponses.map { apodResponse ->
                GalleryItem(
                    title = apodResponse.title,
                    id = apodResponse.date,
                    url = apodResponse.url
                )
            })
        }

        return items
    }


}