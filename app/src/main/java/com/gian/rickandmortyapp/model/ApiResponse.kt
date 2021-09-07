package com.gian.rickandmortyapp.model

import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("info") val info : Info,
    @SerializedName("results") val results : List<Results>
        )


