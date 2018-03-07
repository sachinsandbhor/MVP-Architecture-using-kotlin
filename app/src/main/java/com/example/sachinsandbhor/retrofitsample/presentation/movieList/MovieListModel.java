package com.example.sachinsandbhor.retrofitsample.presentation.movieList;

import com.example.sachinsandbhor.retrofitsample.BuildConfig;
import com.example.sachinsandbhor.retrofitsample.app.MovieService;
import com.example.sachinsandbhor.retrofitsample.model.MovieResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sachin.Sandbhor on 06-02-2018.
 */

public class MovieListModel implements MovieListContract.MovieListModel{

    MovieListContract.apiServices apiServices;

    MovieListContract.MovieListModel.MovieListPresenterCallBack movieListPresenterCallBack;

    public MovieListModel(MovieListContract.MovieListModel.MovieListPresenterCallBack movieListPresenterCallBack) {
        apiServices = MovieService.getClient().create(MovieListContract.apiServices.class);
        this.movieListPresenterCallBack = movieListPresenterCallBack;
    }

    @Override
    public void getMovieList() {
        Observable<MovieResponse> movieResponseObservable = apiServices.getRatedMovies(BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        movieResponseObservable.subscribe(new Observer<MovieResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieResponse movieResponse) {
                movieListPresenterCallBack.onMovieListReceived(movieResponse);
            }

            @Override
            public void onError(Throwable e) {
                movieListPresenterCallBack.onError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
