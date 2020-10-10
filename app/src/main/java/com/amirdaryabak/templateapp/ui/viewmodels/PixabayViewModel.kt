package com.amirdaryabak.templateapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirdaryabak.templateapp.api.Event
import com.amirdaryabak.templateapp.api.Resource
import com.amirdaryabak.templateapp.api.responses.ImageResponse
import com.amirdaryabak.templateapp.db.tables.MyDataModel
import com.amirdaryabak.templateapp.other.Constants
import com.amirdaryabak.templateapp.repositories.PixabayRepository
import kotlinx.coroutines.launch

class PixabayViewModel @ViewModelInject constructor(
    private val repository: PixabayRepository
) : ViewModel() {

    val myDataModelItems = repository.observeAllItems()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    private val _curImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String> = _curImageUrl

    private val _insertItemStatus = MutableLiveData<Event<Resource<MyDataModel>>>()
    val insertItemStatus: LiveData<Event<Resource<MyDataModel>>> = _insertItemStatus

    fun setCurImageUrl(url: String) {
        _curImageUrl.postValue(url)
    }

    fun delete(myDataModel: MyDataModel) = viewModelScope.launch {
        repository.delete(myDataModel)
    }

    fun insert(myDataModel: MyDataModel) = viewModelScope.launch {
        repository.insert(myDataModel)
    }

    fun insertItem(name: String) {
        if(name.isEmpty()) {
            _insertItemStatus.postValue(Event(Resource.error("The fields must not be empty", null)))
            return
        }
        if(name.length > Constants.MAX_NAME_LENGTH) {
            _insertItemStatus.postValue(Event(Resource.error("The name of the item" +
                    "must not exceed ${Constants.MAX_NAME_LENGTH} characters", null)))
            return
        }
        val myDataModel = MyDataModel(name)
        insert(myDataModel)
        setCurImageUrl("")
        _insertItemStatus.postValue(Event(Resource.success(myDataModel)))
    }

    fun searchForImage(imageQuery: String) {
        if(imageQuery.isEmpty()) {
            return
        }
        _images.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.searchForImage(imageQuery)
            _images.value = Event(response)
        }
    }

}