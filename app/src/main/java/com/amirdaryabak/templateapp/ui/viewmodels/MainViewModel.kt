package com.amirdaryabak.templateapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirdaryabak.templateapp.api.Resource
import com.amirdaryabak.templateapp.other.Constants
import com.amirdaryabak.templateapp.repositories.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {
    /*
        private val _value: MutableLiveData<Resource<>> = MutableLiveData()
        val value: LiveData<Resource<>> = _value
        fun signUp(phoneNumber: String) = viewModelScope.launch {
            try {
                _value.postValue(Resource.Loading())
                val response = repository.register(phoneNumber)
                _value.postValue(handleSendCommentsApiResponse(response))
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> _value.postValue((Resource.Error(Constants.NETWORK_FAILURE)))
                    else -> _value.postValue((Resource.Error("مشکل در دریافت اطلاعات")))
                }
            }
        }

    private fun handleTResponse(response: Response<>): Resource<> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    */
}