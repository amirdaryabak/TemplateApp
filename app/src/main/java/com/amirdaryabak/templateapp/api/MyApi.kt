package com.amirdaryabak.templateapp.api

import com.amirdaryabak.templateapp.BuildConfig
import com.amirdaryabak.templateapp.api.responses.ImageResponse
import com.amirdaryabak.templateapp.api.responses.MyDataModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MyApi {

    @GET("")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<MyDataModelResponse>

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<ImageResponse>

}