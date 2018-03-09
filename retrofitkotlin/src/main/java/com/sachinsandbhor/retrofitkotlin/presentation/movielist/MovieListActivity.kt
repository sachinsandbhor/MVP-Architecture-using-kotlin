package com.sachinsandbhor.retrofitkotlin.presentation.movielist

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.sachinsandbhor.retrofitkotlin.R
import com.sachinsandbhor.retrofitkotlin.databinding.ActivityMainBinding
import com.sachinsandbhor.retrofitkotlin.domain.MovieListResponse

/**
 * Created by Sachin.Sandbhor on 07-03-2018.
 */

class MovieListActivity : AppCompatActivity(), MovieListContract.MovieListView{

    private lateinit var movieListDataBinding:ActivityMainBinding
    private lateinit var movieListPresenter :MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieListDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupRecyclerView()
        movieListPresenter = MovieListPresenter(this)

    }

    private fun setupRecyclerView() {
        movieListDataBinding.recyclerview.setHasFixedSize(true)
        movieListDataBinding.recyclerview.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
    }

    override fun setMovie(movieListResponse: MovieListResponse) {
        movieListDataBinding.recyclerview.visibility = View.VISIBLE
        movieListDataBinding.recyclerview.adapter = MovieListAdapter(movieListResponse)
    }

    override fun startLoading() {
        movieListDataBinding.progressBar.visibility = View.VISIBLE
    }

    override fun stopLoading() {
        movieListDataBinding.progressBar.visibility = View.INVISIBLE
    }

    override fun onResume() {
        super.onResume()
        movieListPresenter.setView(this)
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
