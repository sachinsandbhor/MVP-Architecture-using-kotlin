package com.sachinsandbhor.retrofitkotlin.presentation.movielist

import com.sachinsandbhor.retrofitkotlin.MVPInterface
import com.sachinsandbhor.retrofitkotlin.domain.MovieListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sachin.Sandbhor on 07-03-2018.
 */
interface MovieListContract {

    interface MovieListView:MVPInterface.MVPView {
        fun startLoading()
        fun stopLoading()
        fun setMovie(movieListResponse :MovieListResponse)
        fun onError(message:String?)
        fun navigateUser(id:Int)
    }

    interface MovieListPresenter:MVPInterface.MVPPresenter{
        fun getMovieList(pageNo:Int)
        fun setView(view: MovieListContract.MovieListView)
        fun navigateUser(id:Int)
    }

    interface MovieListModel:MVPInterface.MVPModel{
        fun getMovieList(pageNo:Int)
        interface MovieListCallback{
            fun movieListReceived(movieResponse: MovieListResponse)
            fun onError(message: String?)
        }
    }

    interface apiService{
        @GET("movie/top_rated")
        fun getRatedMovies(@Query("api_key") apiKey: String, @Query ("page") page:Int): Observable<MovieListResponse>
    }
}