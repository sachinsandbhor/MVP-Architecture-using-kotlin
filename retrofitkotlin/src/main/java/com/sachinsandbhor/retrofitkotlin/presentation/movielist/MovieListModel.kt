package com.sachinsandbhor.retrofitkotlin.presentation.movielist

import com.sachinsandbhor.retrofitkotlin.BuildConfig
import com.sachinsandbhor.retrofitkotlin.domain.MovieListResponse
import com.sachinsandbhor.retrofitkotlin.network.MovieService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Sachin.Sandbhor on 08-03-2018.
 */
class MovieListModel(var movieListModelCallBack: MovieListContract.MovieListModel.MovieListCallback) : MovieListContract.MovieListModel {

    lateinit var apiService:MovieListContract.apiService

    init {
         apiService = MovieService.getClient()
                .create(MovieListContract.apiService::class.java)
    }

    override fun getMovieList(pageNo:Int) {
        val movieResponseObservable = apiService.getRatedMovies(BuildConfig.API_KEY, pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        movieResponseObservable.subscribe(object : Observer<MovieListResponse> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(movieResponse: MovieListResponse) {
                movieListModelCallBack.movieListReceived(movieResponse)
            }

            override fun onError(e: Throwable) {
                movieListModelCallBack.onError(e.message)
            }

            override fun onComplete() {

            }
        })
    }
}