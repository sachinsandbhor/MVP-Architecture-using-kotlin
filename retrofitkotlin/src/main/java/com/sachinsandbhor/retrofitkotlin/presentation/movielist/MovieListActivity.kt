package com.sachinsandbhor.retrofitkotlin.presentation.movielist

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.sachinsandbhor.retrofitkotlin.R
import com.sachinsandbhor.retrofitkotlin.app.EndlessRecyclerViewScrollListener
import com.sachinsandbhor.retrofitkotlin.databinding.ActivityMainBinding
import com.sachinsandbhor.retrofitkotlin.domain.MovieListResponse
import com.sachinsandbhor.retrofitkotlin.presentation.moviedetail.MovieDetailActivity


/**
 * Created by Sachin.Sandbhor on 07-03-2018.
 */

class MovieListActivity : AppCompatActivity(), MovieListContract.MovieListView{

    private lateinit var movieListDataBinding:ActivityMainBinding
    private lateinit var movieListPresenter :MovieListPresenter
    private lateinit var endlessRecyclerViewScrollListener:EndlessRecyclerViewScrollListener
    var pageNo:Int = 1
    lateinit var movieListAdapter:MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieListDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        movieListPresenter = MovieListPresenter(this)
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        movieListAdapter = MovieListAdapter(movieListPresenter)
        var linearLayoutManager = LinearLayoutManager(this)
        movieListDataBinding.recyclerview.setHasFixedSize(true)
        movieListDataBinding.recyclerview.adapter = movieListAdapter
        movieListDataBinding.recyclerview.layoutManager = linearLayoutManager
        endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                loadMoreData(page)
            }
        }
        movieListDataBinding.recyclerview.addOnScrollListener(endlessRecyclerViewScrollListener)
    }

    private fun loadMoreData(page: Int) {
        pageNo = pageNo+1
        movieListPresenter.getMovieList(pageNo)
    }

    override fun setMovie(movieListResponse: MovieListResponse) {
        if(movieListResponse.page > 1 ){
            movieListAdapter.addAllData(movieListResponse)
        }else {
            movieListDataBinding.recyclerview.visibility = View.VISIBLE
            movieListAdapter.movieList.clear()
            movieListAdapter.addAllData(movieListResponse)
        }
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
        movieListPresenter.getMovieList(1)
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun navigateUser(id: Int) {
        val detailPage = Intent(this, MovieDetailActivity::class.java)
        detailPage.putExtra("ID", id)
        startActivity(detailPage)
    }

}
