package com.uipractice.roomfinder.authentication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uipractice.roomfinder.authentication.model.AuthenticationParameters
import com.uipractice.roomfinder.authentication.model.BaseViewModel
import com.uipractice.roomfinder.authentication.model.ErrorMessage
import com.uipractice.roomfinder.authentication.model.LoginSuccessful
import com.uipractice.roomfinder.authentication.model.RegisterSuccessful
import com.uipractice.roomfinder.webServices.EMAIL
import com.uipractice.roomfinder.webServices.PASSWORD
import com.uipractice.roomfinder.webServices.Responder
import com.uipractice.roomfinder.webServices.RoomFinderApiService
import com.uipractice.roomfinder.webServices.RoomFinderApiServiceImpl
import kotlinx.coroutines.launch
import org.json.JSONObject

class SignUpViewModel: BaseViewModel() {

    private val webServices: RoomFinderApiService = RoomFinderApiServiceImpl()

    private val _createUser = MutableLiveData<RegisterSuccessful>()
    val createUser: LiveData<RegisterSuccessful>
        get() = _createUser

    private val _checkUser = MutableLiveData<LoginSuccessful>()
    val checkUser: LiveData<LoginSuccessful>
        get() = _checkUser

    fun createUser(email: String, password: String) {
        viewModelScope.launch {
            setLoading(true)
            val parameters = JSONObject()
            parameters.put(EMAIL, email)
            parameters.put(PASSWORD, password)
            webServices.createUser(parameters, object : Responder<RegisterSuccessful, ErrorMessage> {
                override fun onSuccess(result: RegisterSuccessful) {
                    _createUser.value = result
                    setLoading(false)
                }

                override fun onFailure(error: ErrorMessage) {
                    setFailureMessage("Error: ${error.error}")
                    setLoading(false)
                }

                override fun onFailure(error: String) {
                    setFailureMessage("Error: $error")
                    setLoading(false)
                }
            })
        }
    }

    fun createUserRetrofit(email: String, password: String) {
        viewModelScope.launch {
            setLoading(true)
            val parameters = AuthenticationParameters(email, password)
            webServices.createUserRetrofit(parameters, object : Responder<RegisterSuccessful, ErrorMessage> {
                override fun onSuccess(result: RegisterSuccessful) {
                    _createUser.value = result
                    setLoading(false)
                }

                override fun onFailure(error: ErrorMessage) {
                    setFailureMessage("Error: ${error.error}")
                    setLoading(false)
                }

                override fun onFailure(error: String) {
                    setFailureMessage("Error: $error")
                    setLoading(false)
                }
            })
        }
    }

    fun checkUser(email: String, password: String) {
        viewModelScope.launch {
            setLoading(true)
            val parameters = JSONObject()
            parameters.put(EMAIL, email)
            parameters.put(PASSWORD, password)
            webServices.checkUser(parameters, object : Responder<LoginSuccessful, ErrorMessage> {
                override fun onSuccess(result: LoginSuccessful) {
                    _checkUser.value = result
                    setLoading(false)
                }

                override fun onFailure(error: ErrorMessage) {
                    setFailureMessage("Error: ${error.error}")
                    setLoading(false)
                }

                override fun onFailure(error: String) {
                    setFailureMessage("Error: $error")
                    setLoading(false)
                }
            })
        }
    }

    fun checkUserRetrofit(email: String, password: String) {
        viewModelScope.launch {
            setLoading(true)
            val parameters = AuthenticationParameters(email, password)
            webServices.checkUserRetrofit(parameters, object : Responder<LoginSuccessful, ErrorMessage> {
                override fun onSuccess(result: LoginSuccessful) {
                    _checkUser.value = result
                    setLoading(false)
                }

                override fun onFailure(error: ErrorMessage) {
                    setFailureMessage("Error: ${error.error}")
                    setLoading(false)
                }

                override fun onFailure(error: String) {
                    setFailureMessage("Error: $error")
                    setLoading(false)
                }
            })
        }
    }

}