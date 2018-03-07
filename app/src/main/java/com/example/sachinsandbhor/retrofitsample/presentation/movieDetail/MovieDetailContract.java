package com.example.sachinsandbhor.retrofitsample.presentation.movieDetail;

import com.example.sachinsandbhor.retrofitsample.MVP;
import com.example.sachinsandbhor.retrofitsample.model.Movie;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sachin.Sandbhor on 08-02-2018.
 */

public interface MovieDetailContract {

    public interface MovieDetailView extends MVP.View{
        void startLoading();

        void stopLoading();

        void showMovieDetails(Movie movie);

        void onError(String errorMessage);
    }

    public interface MovieDetailPresenter extends MVP.Presenter{

        void getMovieDetail(int id);

        void detach();
    }

    public interface MovieDetailModel extends MVP.Model{

        void getMovieDetail(int id);

        interface movieDetailCallBack{

            void onMovieDetailReceived(Movie movie);

            void onError(String message);
        }
    }

    interface MovieApi {

        @GET("movie/{id}")
        Observable<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
    }
}
