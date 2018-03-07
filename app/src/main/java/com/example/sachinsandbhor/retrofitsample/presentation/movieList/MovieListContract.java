package com.example.sachinsandbhor.retrofitsample.presentation.movieList;

import com.example.sachinsandbhor.retrofitsample.MVP;
import com.example.sachinsandbhor.retrofitsample.model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sachin.Sandbhor on 06-02-2018.
 */

public interface MovieListContract {

    interface MovieListView extends MVP.View{

        void setMovieList(MovieResponse movieResponse);

        void startLoadingPost();

        void stopLoadingPost();

        void navigateToMovieDetail(int id);

        void onError(String errorMessage);

    }

    interface MovieListPresenter extends MVP.Presenter{

        void getMovieList();

        void navigateToMovieDetail(int id);

        void detach();

        void onError(String errorMessage);

        void setView(MovieListContract.MovieListView movieListView);

    }

    interface MovieListModel extends MVP.Model{

        void getMovieList();

        interface MovieListPresenterCallBack {

            void onMovieListReceived(MovieResponse movieResponse);

            void onError(String errorMessage);
        }

    }

    interface apiServices {

        @GET("movie/top_rated")
        Observable<MovieResponse> getRatedMovies(@Query("api_key") String apiKey);
    }

}
