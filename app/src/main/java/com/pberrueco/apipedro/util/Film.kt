package com.pberrueco.apipedro.util

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("idSerie")
    val id: Int,
    @SerializedName("name")
    val name:  String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("ranking")
    val ranking: Int
)
