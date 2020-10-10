package com.amirdaryabak.templateapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amirdaryabak.templateapp.api.Resource
import com.amirdaryabak.templateapp.api.responses.ImageResponse
import com.amirdaryabak.templateapp.db.tables.MyDataModel

class FakePixabayRepository : PixabayRepository {

    private val myDataModelItems = mutableListOf<MyDataModel>()

    private val observableItems = MutableLiveData<List<MyDataModel>>(myDataModelItems)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableItems.postValue(myDataModelItems)
    }

    override suspend fun insert(myDataModel: MyDataModel) {
        myDataModelItems.add(myDataModel)
        refreshLiveData()
    }

    override suspend fun delete(myDataModel: MyDataModel) {
        myDataModelItems.remove(myDataModel)
        refreshLiveData()
    }

    override fun observeAllItems(): LiveData<List<MyDataModel>> {
        return observableItems
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if(shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ImageResponse(listOf(), 0, 0))
        }
    }


}