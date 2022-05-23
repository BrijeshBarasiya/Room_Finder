package com.uipractice.roomfinder.webServices

import com.google.gson.Gson
import com.uipractice.roomfinder.authentication.model.AuthenticationParameters
import com.uipractice.roomfinder.authentication.model.ErrorMessage
import com.uipractice.roomfinder.authentication.model.LoginSuccessful
import com.uipractice.roomfinder.authentication.model.RegisterSuccessful
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

enum class IdentifyApiCall {
    UsingHttp,
    UsingRetrofit
}

enum class APIs(val url: URL, val responseCode: Int, val requestMethod: String) {
    SignUpAPI(URL("${BaseURL}register"), 200, "POST"),
    SignInAPI(URL("${BaseURL}login"), 200, "POST")
}

interface RoomFinderApiService {
    fun createUser(parameters: JSONObject, responder: Responder<RegisterSuccessful, ErrorMessage>)
    fun createUserRetrofit(parameters: AuthenticationParameters, responder: Responder<RegisterSuccessful, ErrorMessage>)
    fun checkUser(parameters: JSONObject, responder: Responder<LoginSuccessful, ErrorMessage>)
    fun checkUserRetrofit(parameters: AuthenticationParameters, responder: Responder<LoginSuccessful, ErrorMessage>)
}

interface RoomFinderRetrofitApiServices{
    @POST("register")
    fun createUser(@Body parameters: AuthenticationParameters): Call<RegisterSuccessful>
    @POST("login")
    fun checkUser(@Body parameters: AuthenticationParameters): Call<LoginSuccessful>
}

interface Responder<T, U> {
    fun onSuccess(result: T)
    fun onFailure(error: U)
    fun onFailure(error: String)
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
                    try {
                        BufferedReader(InputStreamReader(errorStream)).use { responseData ->
                            val result = Gson().fromJson(responseData.readText(), errorClass)
                            CoroutineScope(Dispatchers.Main).launch {
                                responder.onFailure(result)
                            }
                        }
                    } catch (ex: Exception) {
                        responder.onFailure(getErrorMessage(responseCode))
                    }
                }
            }
        }
    }

    private fun<T, U> retrofitRequest(builder: Call<T>, errorClass: Class<U>, responseCode: Int, responder: Responder<T, U>) {
        builder.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    if (response.code() == responseCode) {
                        response.body()?.let {
                            responder.onSuccess(it)
                        }
                    } else {
                        responder.onFailure(getErrorMessage(responseCode))
                    }
                } else {
                    try {
                        response.errorBody()?.let {
                            val result = Gson().fromJson(it.charStream().readText(), errorClass)
                            responder.onFailure(result)
                        }
                    } catch (ex: Exception) {
                        responder.onFailure(getErrorMessage(responseCode))
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                responder.onFailure(t.toString())
            }
        })
    }

    private fun getErrorMessage(responseCode: Int): String {
        return  when (responseCode) {
            404 -> "The server has not found anything matching the Request-URI"
            else -> "Un Handle Exception"
        }
    }

    override fun createUser(parameters: JSONObject, responder: Responder<RegisterSuccessful, ErrorMessage>) {
        request(RegisterSuccessful::class.java, ErrorMessage::class.java, APIs.SignUpAPI, parameters, responder)
    }

    override fun createUserRetrofit(parameters: AuthenticationParameters, responder: Responder<RegisterSuccessful, ErrorMessage>) {
        val builder = retrofitBuilder.createUser(parameters)
        retrofitRequest(builder, ErrorMessage::class.java, 200, responder)
    }

    override fun checkUser(parameters: JSONObject, responder: Responder<LoginSuccessful, ErrorMessage>) {
        request(LoginSuccessful::class.java, ErrorMessage::class.java, APIs.SignInAPI, parameters, responder)
    }

    override fun checkUserRetrofit(parameters: AuthenticationParameters, responder: Responder<LoginSuccessful, ErrorMessage>) {
        val builder = retrofitBuilder.checkUser(parameters)
        retrofitRequest(builder, ErrorMessage::class.java, 200, responder)
    }

}