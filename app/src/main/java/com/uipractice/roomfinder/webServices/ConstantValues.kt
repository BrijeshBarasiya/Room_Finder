package com.uipractice.roomfinder.webServices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Constant Values
const val ApiIdentity = "API_IDENTITY"
const val EMAIL = "email"
const val PASSWORD = "password"
const val BaseURL = "https://reqres.in/api/"

// Retrofit
var apiIdentifier: IdentifyApiCall = IdentifyApiCall.UsingHttp
val retrofitBuilder: RoomFinderRetrofitApiServices by lazy {
    return@lazy Retrofit.Builder().baseUrl(BaseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RoomFinderRetrofitApiServices::class.java)
}
