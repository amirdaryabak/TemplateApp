package com.amirdaryabak.templateapp.repository

import androidx.paging.PagingSource
import com.amirdaryabak.templateapp.api.MyApi
import com.amirdaryabak.templateapp.api.responses.MyDataModelResponse
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class PagingSource(
    private val myApi: MyApi,
    private val query: String
) : PagingSource<Int, MyDataModelResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyDataModelResponse> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = myApi.search(query, position, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}