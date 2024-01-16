package com.pberrueco.apipedro.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pberrueco.apipedro.conexion.RetrofitClient
import com.pberrueco.apipedro.databinding.FragmentMainBinding
import com.pberrueco.apipedro.util.Film
import com.pberrueco.apipedro.util.FilmAdapter
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class MainFragment : Fragment() {

    private lateinit var _binding: FragmentMainBinding
    private val binding: FragmentMainBinding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    // En el método onViewCreated de MainFragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // LOGICA DE LA APLICACION

        // Lanzar la corrutina en el contexto de Dispatchers.IO
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Obtener la lista de películas de la API
                val filmList: List<Film> = getFilmListFromApi()
                Log.d("API_CALL", "Llamada a la API exitosa. Cantidad de películas: ${filmList.size}")
                // Actualizar la interfaz de usuario en el hilo principal
                withContext(Dispatchers.Main) {
                    // Configuramos el adaptador para el RecyclerView
                    binding.rvFilms.adapter = FilmAdapter(filmList)

                    // Configuramos el LayoutManager (puedes elegir entre LinearLayoutManager, GridLayoutManager, etc.)
                    val layoutManager = LinearLayoutManager(requireContext())
                    binding.rvFilms.layoutManager = layoutManager
                }
            } catch (e: Exception) {
                Log.e("API_CALL", "Error en la llamada a la API: ${e.message}")

                // Manejar errores, como falta de conexión, etc.
                // Puedes mostrar un mensaje de error o realizar otras acciones.
                e.printStackTrace()
            }
        }
    }

    private suspend fun getFilmListFromApi(): List<Film> {
        return try {
            val response = RetrofitClient.getClient().getMovies()
            if (response.isSuccessful) {
                val films = response.body() ?: emptyList()
                Log.d("API_CALL", "Llamada a la API exitosa. Cantidad de películas: ${films.size}")
                films
            } else {
                // Manejar errores de la respuesta
                Log.e("API_CALL", "Error en la llamada a la API: ${response.code()}")
                emptyList()
            }
        } catch (e: Exception) {
            // Manejar errores, como falta de conexión, etc.
            // Puedes mostrar un mensaje de error o realizar otras acciones.
            Log.e("API_CALL", "Error en la llamada a la API: ${e.message}", e)
            emptyList()
        }
    }



}