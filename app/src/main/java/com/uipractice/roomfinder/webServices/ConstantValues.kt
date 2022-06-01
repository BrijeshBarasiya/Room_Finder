package com.uipractice.roomfinder.webServices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Constant Values
const val ApiIdentity = "API_IDENTITY"
const val EMAIL = "email"
const val PASSWORD = "password"
const val BaseURL = "https://reqres.in/api/"
const val APP_NAME = "ROOM FINDER"
const val TOKEN = "TOKEN"
const val isLOGIN = "isLOGIN"

// Retrofit
var apiIdentifier: IdentifyApiCall = IdentifyApiCall.UsingHttp
val retrofitBuilder: RoomFinderRetrofitApiServices by lazy {
    return@lazy Retrofit.Builder().baseUrl(BaseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RoomFinderRetrofitApiServices::class.java)
}
