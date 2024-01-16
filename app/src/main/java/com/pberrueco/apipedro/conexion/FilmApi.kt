package com.pberrueco.apipedro.conexion
import com.pberrueco.apipedro.util.Film
import retrofit2.Response
import retrofit2.http.GET


interface FilmApi {
    @GET("series")
    suspend fun getMovies(): Response<List<Film>>
}