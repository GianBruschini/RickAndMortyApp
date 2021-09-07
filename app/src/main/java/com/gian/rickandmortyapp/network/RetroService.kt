package com.gian.rickandmortyapp.network

import com.gian.rickandmortyapp.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RetroService {

    @GET
    suspend fun getCharacters(@Url url: String?): ApiResponse
}