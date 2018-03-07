package com.example.sachinsandbhor.retrofitsample.presentation.movieDetail;

import com.example.sachinsandbhor.retrofitsample.BuildConfig;
import com.example.sachinsandbhor.retrofitsample.app.MovieService;
import com.example.sachinsandbhor.retrofitsample.model.Movie;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sachin.Sandbhor on 08-02-2018.
 */

public class MovieDetailModel implements MovieDetailContract.MovieDetailModel {

    MovieDetailContract.MovieDetailModel.movieDetailCallBack movieDetailCallBack;
    MovieDetailContract.MovieApi apiServices;

    public MovieDetailModel(MovieDetailContract.MovieDetailModel.movieDetailCallBack movieDetailCallBack) {
        apiServices = MovieService.getClient().create(MovieDetailContract.MovieApi.class);
        this.movieDetailCallBack = movieDetailCallBack;
    }

    @Override
    public void getMovieDetail(int id){
        Observable<Movie> movieResponseObservable = apiServices.getMovieDetails(id, BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        movieResponseObservable.subscribe(new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Movie movie) {
                movieDetailCallBack.onMovieDetailReceived(movie);
            }

            @Override
            public void onError(Throwable e) {
                movieDetailCallBack.onError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }


}
