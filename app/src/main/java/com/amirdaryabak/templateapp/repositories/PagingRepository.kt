package com.amirdaryabak.templateapp.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.amirdaryabak.templateapp.api.MyApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PagingRepository @Inject constructor(private val myApi: MyApi) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingSource(myApi, query) }
        ).liveData
}