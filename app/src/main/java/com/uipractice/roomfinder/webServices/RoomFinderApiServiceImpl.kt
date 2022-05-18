package com.uipractice.roomfinder.webServices

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL


enum class APIs(val url: URL, val responseCode: Int, val requestMethod: String) {
    SignUpAPI(URL("${BaseURL}users"), 201, "POST"),
}

interface RoomFinderApiService {
    fun createUser(parameters: JSONObject, responder: Responder<CreateUserValues, ErrorMessage>)
}

interface Responder<T, U> {
    fun onSuccess(result: T)
    fun onFailure(error: U)
}

class RoomFinderApiServiceImpl: RoomFinderApiService {

    private fun <T, U> request(responseClass: Class<T>, errorClass: Class<U>, api: APIs, parameters: JSONObject?, responder: Responder<T, U>) {
        CoroutineScope(Dispatchers.IO).launch {
            (api.url.openConnection() as HttpURLConnection).apply {
                requestMethod = api.requestMethod
                setRequestProperty("Content-Type", "application/json")
                if (parameters != null) {
                    val streamWriter = OutputStreamWriter(outputStream)
                    streamWriter.write("$parameters")
                    streamWriter.flush()
                }
                if (responseCode == api.responseCode) {
                    BufferedReader(InputStreamReader(inputStream)).use { responseData ->
                        val result = Gson().fromJson(responseData.readText(), responseClass)
                        CoroutineScope(Dispatchers.Main).launch {
                            responder.onSuccess(result)
                        }
                    }
                } else {
                    CoroutineScope(Dispatchers.Main).launch {
                        when (responseCode) {
                            //204 -> responder.onFailure("Data Not Found!")
                            //404 -> responder.onFailure("Response Code: $responseCode and Message: $responseMessage")
                            400 -> {
                                BufferedReader(InputStreamReader(inputStream)).use { responseData ->
                                    val result = Gson().fromJson(responseData.readText(), errorClass)
                                    CoroutineScope(Dispatchers.Main).launch {
                                        responder.onFailure(result)
                                    }
                                }
                            }
                            //else -> responder.onFailure("Response Code: $responseCode and Message: $responseMessage")
                        }
                    }
                }
            }
        }
    }

    override fun createUser(parameters: JSONObject, responder: Responder<CreateUserValues, ErrorMessage>) {
        request(CreateUserValues::class.java, ErrorMessage::class.java, APIs.SignUpAPI, parameters, responder)
    }

}