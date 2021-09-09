package com.gian.rickandmortyapp.network

import com.gian.rickandmortyapp.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RetroService {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): ApiResponse
}