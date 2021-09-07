package com.gian.rickandmortyapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {
        val baseURLdeLaApiParaAcceder = "https://rickandmortyapi.com/api/"

        fun getRetroInstance():Retrofit{
            return Retrofit.
            Builder().
            baseUrl(baseURLdeLaApiParaAcceder).
            addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}