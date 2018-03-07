package com.example.sachinsandbhor.retrofitsample.presentation.movieDetail;


import com.example.sachinsandbhor.retrofitsample.model.Movie;

/**
 * Created by Sachin.Sandbhor on 06-02-2018.
 */

public class DetailPresenter implements MovieDetailContract.MovieDetailPresenter, MovieDetailContract.MovieDetailModel.movieDetailCallBack {

    private MovieDetailContract.MovieDetailView movieDetailView;
    private MovieDetailModel movieDetailModel;

    public DetailPresenter(MovieDetailContract.MovieDetailView movieDetailView) {
        this.movieDetailView = movieDetailView;
        movieDetailModel = new MovieDetailModel(this);
    }

    public void getMovieDetail(int id){
        movieDetailView.startLoading();
        movieDetailModel.getMovieDetail(id);
    }

    @Override
    public void detach() {
        if(null != movieDetailView){
            movieDetailView = null;
        }
    }

    @Override
    public void onMovieDetailReceived(Movie movie) {
        movieDetailView.stopLoading();
        movieDetailView.showMovieDetails(movie);
    }

    @Override
    public void onError(String message) {
        movieDetailView.onError(message);
    }
}
