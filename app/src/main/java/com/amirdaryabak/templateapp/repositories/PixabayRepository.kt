package com.amirdaryabak.templateapp.repositories

import androidx.lifecycle.LiveData
import com.amirdaryabak.templateapp.api.Resource
import com.amirdaryabak.templateapp.api.responses.ImageResponse
import com.amirdaryabak.templateapp.db.tables.MyDataModel

interface PixabayRepository {

    suspend fun insert(myDataModel: MyDataModel)

    suspend fun delete(myDataModel: MyDataModel)

    fun observeAllItems(): LiveData<List<MyDataModel>>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}