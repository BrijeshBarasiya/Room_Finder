package com.uipractice.roomfinder.authentication.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    private val _failureMessage = MutableLiveData<String>()
    val failureMessage: LiveData<String>
        get() = _failureMessage

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun setFailureMessage(message: String) {
        _failureMessage.value = message
    }

}