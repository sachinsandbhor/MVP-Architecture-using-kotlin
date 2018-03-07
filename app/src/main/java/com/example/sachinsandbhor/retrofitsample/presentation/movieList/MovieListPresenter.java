package com.example.sachinsandbhor.retrofitsample.presentation.movieList;

import com.example.sachinsandbhor.retrofitsample.model.MovieResponse;

/**
 * Created by Sachin.Sandbhor on 05-02-2018.
 */

public class MovieListPresenter implements MovieListContract.MovieListPresenter, MovieListContract.MovieListModel.MovieListPresenterCallBack {

    MovieListContract.MovieListView movieListView;
    MovieListContract.MovieListModel movieListModel;

    public MovieListPresenter(MovieListContract.MovieListView movieListView) {
        this.movieListView = movieListView;
        movieListModel = new MovieListModel(this);
    }

    @Override
    public void getMovieList() {
        movieListView.startLoadingPost();
        movieListModel.getMovieList();
    }

    @Override
    public void onMovieListReceived(MovieResponse movieResponse) {
        movieListView.stopLoadingPost();
        if(movieListView != null){
            movieListView.setMovieList(movieResponse);
        }
    }

    @Override
    public void navigateToMovieDetail(int id) {
        if(null != movieListView) {
            movieListView.navigateToMovieDetail(id);
        }
    }

    @Override
    public void detach() {
        movieListView = null;
    }

    @Override
    public void onError(String errorMessage) {
        if(null != movieListView) {
            movieListView.onError(errorMessage);
        }
    }

    @Override
    public void setView(MovieListContract.MovieListView movieListView) {
        this.movieListView = movieListView;
    }
}
