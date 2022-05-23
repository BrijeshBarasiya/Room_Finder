package com.uipractice.roomfinder.webServices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val ApiIdentity = "API_IDENTITY"
const val BaseURL = "https://reqres.in/api/"
var apiIdentifier: IdentifyApiCall = IdentifyApiCall.UsingHttp
val retrofitBuilder: RoomFinderRetrofitApiServices by lazy {
    return@lazy Retrofit.Builder().baseUrl(BaseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RoomFinderRetrofitApiServices::class.java)
}
