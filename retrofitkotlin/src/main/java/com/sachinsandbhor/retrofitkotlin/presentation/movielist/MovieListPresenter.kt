package com.sachinsandbhor.retrofitkotlin.presentation.movielist

import com.sachinsandbhor.retrofitkotlin.domain.MovieListResponse


/**
 * Created by Sachin.Sandbhor on 08-03-2018.
 */
class MovieListPresenter(private var view: MovieListContract.MovieListView) :MovieListContract.MovieListPresenter,MovieListContract.MovieListModel.MovieListCallback {


    override fun setView(movieListView: MovieListContract.MovieListView) {
        view = movieListView
        getMovieList()
    }

    override fun getMovieList() {
        var movieListModel:MovieListContract.MovieListModel = MovieListModel(this)
        movieListModel.getMovieList()
    }

    override fun movieListReceived(movieResponse: MovieListResponse) {
       view.setMovie(movieResponse)
    }

    override fun onError(message: String?) {
        view.onError(message)
    }
}