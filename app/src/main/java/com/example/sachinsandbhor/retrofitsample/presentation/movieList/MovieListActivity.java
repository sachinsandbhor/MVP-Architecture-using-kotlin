package com.example.sachinsandbhor.retrofitsample.presentation.movieList;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.sachinsandbhor.retrofitsample.R;
import com.example.sachinsandbhor.retrofitsample.app.NetworkUtils;
import com.example.sachinsandbhor.retrofitsample.databinding.ActivityMainBinding;
import com.example.sachinsandbhor.retrofitsample.model.Movie;
import com.example.sachinsandbhor.retrofitsample.model.MovieResponse;
import com.example.sachinsandbhor.retrofitsample.presentation.movieDetail.DetailActivity;

import java.util.List;



public class MovieListActivity extends AppCompatActivity implements MovieListContract.MovieListView{

    public MovieListPresenter moviePresenter;

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.recyclerview.setHasFixedSize(true);
        activityMainBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        moviePresenter = new MovieListPresenter(this);

    }

    @Override
    protected void onDestroy() {
        moviePresenter.detach();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        moviePresenter.setView(this);
        if(NetworkUtils.isNetworkAvaialble(this)) {
            moviePresenter.getMovieList();
        }else {
            onError("No internet connection");
        }
    }

    @Override
    public void setMovieList(MovieResponse movieResponse) {
        List<Movie> movies = movieResponse.getResults();
        activityMainBinding.recyclerview.setAdapter(new MovieListAdapter(movies, moviePresenter));
    }

    @Override
    public void startLoadingPost() {
        activityMainBinding.progressBar2.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoadingPost() {
        activityMainBinding.progressBar2.setVisibility(View.GONE);
        activityMainBinding.recyclerview.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToMovieDetail(int id) {
        Intent detailPage = new Intent(this, DetailActivity.class);
        detailPage.putExtra("ID", id);
        startActivity(detailPage);
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
