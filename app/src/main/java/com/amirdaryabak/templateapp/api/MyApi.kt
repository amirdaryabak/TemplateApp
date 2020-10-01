package com.amirdaryabak.templateapp.api

import com.amirdaryabak.templateapp.api.responses.MyDataModelResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MyApi {

    @GET("")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<MyDataModelResponse>

}