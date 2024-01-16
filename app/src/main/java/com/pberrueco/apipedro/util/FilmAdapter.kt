package com.pberrueco.apipedro.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.pberrueco.apipedro.databinding.ItemFilmBinding

class FilmAdapter(private var film: List<Film>) :
    RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.FilmViewHolder {
        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmAdapter.FilmViewHolder, position: Int) {
        val film = film[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int {
        return film.size
    }

    inner class FilmViewHolder(val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) {
            binding.tvCard.text = film.name

        }

    }

}