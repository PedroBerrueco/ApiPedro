package com.pberrueco.apipedro.conexion

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private const val BASE_URL = "http://192.168.1.127:8080/api/v1/"

        fun getClient(): FilmApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(FilmApi::class.java)
        }
    }
}
