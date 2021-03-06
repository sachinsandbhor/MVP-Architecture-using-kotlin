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

class MovieListAdapter(var context: MovieListPresenter) : RecyclerView.Adapter<MovieViewHolder>() {

    var movieList: MutableList<Movie> = ArrayList()
    lateinit var movieListPresenter: MovieListPresenter

    init {
        movieListPresenter = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val movieListRowBinding:MovieListRowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.movie_list_row, parent, false)
        return MovieViewHolder(movieListRowBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder!!.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    fun addAllData(movieListResponse: MovieListResponse) {
        movieList.addAll(movieListResponse.results as MutableList<Movie>)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private var binding: MovieListRowBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding
        }
        fun bind(movie:Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

}
