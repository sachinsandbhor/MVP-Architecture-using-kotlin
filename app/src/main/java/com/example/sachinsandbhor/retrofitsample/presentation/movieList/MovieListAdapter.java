package com.example.sachinsandbhor.retrofitsample.presentation.movieList;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sachinsandbhor.retrofitsample.R;
import com.example.sachinsandbhor.retrofitsample.databinding.MovieListRowBinding;
import com.example.sachinsandbhor.retrofitsample.model.Movie;

import java.util.List;

/**
 * Created by Sachin.Sandbhor on 31-01-2018.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>{
    private List<Movie> movies;
    private MovieListPresenter moviePresenter;


    public MovieListAdapter(List<Movie> movies, MovieListPresenter context) {
        this.movies = movies;
        this.moviePresenter = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View movieContainer = layoutInflater.inflate(R.layout.movie_list_row, parent, false);
        return new MovieViewHolder(movieContainer);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder{

        MovieListRowBinding movieListRowBinding;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieListRowBinding = DataBindingUtil.bind(itemView);
            movieListRowBinding.setPresenter(moviePresenter);
        }

        public void bind(Movie movie){
            movieListRowBinding.setMovie(movie);
        }
    }
}

