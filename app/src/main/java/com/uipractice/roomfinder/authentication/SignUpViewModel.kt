package com.uipractice.roomfinder.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uipractice.roomfinder.webServices.CreateUserValues
import com.uipractice.roomfinder.webServices.ErrorMessage
import com.uipractice.roomfinder.webServices.RoomFinderApiServiceImpl
import com.uipractice.roomfinder.webServices.Responder
import com.uipractice.roomfinder.webServices.RoomFinderApiService
import kotlinx.coroutines.launch
import org.json.JSONObject

class SignUpViewModel: ViewModel() {

    private val webServices: RoomFinderApiService = RoomFinderApiServiceImpl()

    private val _createUserObserver = MutableLiveData<String>()
    val createUserObserver: LiveData<String>
        get() = _createUserObserver

    fun createUser(parameters: JSONObject) {
        viewModelScope.launch {
            webServices.createUser(parameters, object : Responder<CreateUserValues, ErrorMessage> {
                override fun onSuccess(result: CreateUserValues) {
                    _createUserObserver.value = result.toString()
                }

                override fun onFailure(error: ErrorMessage) {
                    _createUserObserver.value = error.error
                }
            })
        }
    }

}