package com.example.sachinsandbhor.retrofitsample.presentation.movieDetail;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sachinsandbhor.retrofitsample.R;
import com.example.sachinsandbhor.retrofitsample.databinding.ActivityDetailBinding;
import com.example.sachinsandbhor.retrofitsample.model.Movie;

public class DetailActivity extends AppCompatActivity implements MovieDetailContract.MovieDetailView {

    private ActivityDetailBinding activityDetailBinding;
    private DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        detailPresenter = new DetailPresenter(this);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getId();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getId() {
        if(null != getIntent() && getIntent().hasExtra("ID")){
            detailPresenter.getMovieDetail(getIntent().getExtras().getInt("ID"));
        }
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showMovieDetails(Movie movie) {
        activityDetailBinding.setMovie(movie);
    }


    @Override
    public void onError(String errorMessage) {

    }

    @Override
    protected void onDestroy() {
        detailPresenter.detach();
        super.onDestroy();
    }
}
