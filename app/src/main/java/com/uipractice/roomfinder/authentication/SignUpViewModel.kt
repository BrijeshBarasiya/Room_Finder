package com.uipractice.roomfinder.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uipractice.roomfinder.webServices.ErrorMessage
import com.uipractice.roomfinder.webServices.ObserverValue
import com.uipractice.roomfinder.webServices.RegisterSuccessful
import com.uipractice.roomfinder.webServices.Responder
import com.uipractice.roomfinder.webServices.RoomFinderApiService
import com.uipractice.roomfinder.webServices.RoomFinderApiServiceImpl
import kotlinx.coroutines.launch
import org.json.JSONObject

class SignUpViewModel: ViewModel() {

    private val webServices: RoomFinderApiService = RoomFinderApiServiceImpl()

    private val _createUserObserver = MutableLiveData<ObserverValue>()
    val createUserObserver: LiveData<ObserverValue>
        get() = _createUserObserver
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun createUser(parameters: JSONObject) {
        _isLoading.value = true
        viewModelScope.launch {
            webServices.createUser(parameters, object : Responder<RegisterSuccessful, ErrorMessage> {
                override fun onSuccess(result: RegisterSuccessful) {
                    val observer = ObserverValue(true, "Token is: ${result.token}")
                    _createUserObserver.value = observer
                    _isLoading.value = false
                }

                override fun onFailure(error: ErrorMessage, responseCode: Int) {
                    val observer = ObserverValue(false, "Response Code: $responseCode. Error: ${error.error}")
                    _createUserObserver.value = observer
                    _isLoading.value = false
                }
            })
        }
    }

    fun checkUser(parameters: JSONObject) {
        _isLoading.value = true
        viewModelScope.launch {
            webServices.createUser(parameters, object : Responder<RegisterSuccessful, ErrorMessage> {
                override fun onSuccess(result: RegisterSuccessful) {
                    val observer = ObserverValue(true, "Token is: ${result.token}")
                    _createUserObserver.value = observer
                    _isLoading.value = false
                }

                override fun onFailure(error: ErrorMessage, responseCode: Int) {
                    val observer = ObserverValue(false, "Response Code: $responseCode. Error: ${error.error}")
                    _createUserObserver.value = observer
                    _isLoading.value = false
                }
            })
        }
    }

}