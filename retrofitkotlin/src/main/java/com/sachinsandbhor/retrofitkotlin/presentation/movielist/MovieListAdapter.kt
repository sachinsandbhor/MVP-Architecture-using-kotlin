package com.sachinsandbhor.retrofitkotlin.presentation.movielist

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sachinsandbhor.retrofitkotlin.R
import com.sachinsandbhor.retrofitkotlin.databinding.MovieListRowBinding
import com.sachinsandbhor.retrofitkotlin.domain.Movie
import com.sachinsandbhor.retrofitkotlin.domain.MovieListResponse
import com.sachinsandbhor.retrofitkotlin.presentation.movielist.MovieListAdapter.MovieViewHolder
import com.squareup.picasso.Picasso

class MovieListAdapter(movieListResponse: MovieListResponse) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movieList:List<Movie> = ArrayList()

    init {
        movieList = movieListResponse.results
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val movieListRowBinding:MovieListRowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.movie_list_row, parent, false)
        return MovieViewHolder(movieListRowBinding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder?, position: Int) {
        holder!!.bind(movieList[position])
    }

    inner class MovieViewHolder(var binding: MovieListRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie:Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
}
