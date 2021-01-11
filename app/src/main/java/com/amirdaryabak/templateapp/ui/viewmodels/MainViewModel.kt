package com.amirdaryabak.templateapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.amirdaryabak.templateapp.api.Event
import com.amirdaryabak.templateapp.api.ResourceTest
import com.amirdaryabak.templateapp.models.MyDataModelAppLevel
import com.amirdaryabak.templateapp.repositories.MainRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val tasksEventChannel = Channel<HandleEvent>()
    val tasksEvent = tasksEventChannel.receiveAsFlow()

    /*fun apiCall(p1: String, p2: Int, p3: Int) = liveData {
        emit(Event(ResourceTest.loading(null)))
        emit(Event(repository.apiCall(p1, p2, p3)))
    }*/

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


    sealed class HandleEvent {
        object Obj : HandleEvent()
        data class DT(val myDataModelAppLevel: MyDataModelAppLevel) : HandleEvent()
    }
}