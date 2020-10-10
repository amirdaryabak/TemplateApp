package com.amirdaryabak.templateapp.repositories

import androidx.lifecycle.LiveData
import com.amirdaryabak.templateapp.api.MyApi
import com.amirdaryabak.templateapp.api.Resource
import com.amirdaryabak.templateapp.api.responses.ImageResponse
import com.amirdaryabak.templateapp.db.MyDao
import com.amirdaryabak.templateapp.db.tables.MyDataModel
import javax.inject.Inject

class DefaultPixabayRepository @Inject constructor(
    val myDao: MyDao,
    val myApi: MyApi
) : PixabayRepository {

    override suspend fun insert(myDataModel: MyDataModel) {
        myDao.insert(myDataModel)
    }

    override suspend fun delete(myDataModel: MyDataModel) {
        myDao.delete(myDataModel)
    }

    override fun observeAllItems(): LiveData<List<MyDataModel>> {
        return myDao.observeAllItems()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = myApi.searchForImage(imageQuery)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurred", null)
            } else {
                Resource.error("An unknown error occurred", null)
            }
        } catch (t: Throwable) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}