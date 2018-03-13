package com.sachinsandbhor.retrofitkotlin.presentation.movielist

import com.sachinsandbhor.retrofitkotlin.domain.MovieListResponse


/**
 * Created by Sachin.Sandbhor on 08-03-2018.
 */
class MovieListPresenter(private var view: MovieListContract.MovieListView) :MovieListContract.MovieListPresenter,MovieListContract.MovieListModel.MovieListCallback {
    override fun navigateUser(id: Int) {
        view.navigateUser(id)
    }


    override fun setView(movieListView: MovieListContract.MovieListView) {
        view = movieListView
    }

    override fun getMovieList(pageNo : Int) {
        var movieListModel:MovieListContract.MovieListModel = MovieListModel(this)
        movieListModel.getMovieList(pageNo)
    }

    override fun movieListReceived(movieResponse: MovieListResponse) {
       view.setMovie(movieResponse)
    }

    override fun onError(message: String?) {
        view.onError(message)
    }
}