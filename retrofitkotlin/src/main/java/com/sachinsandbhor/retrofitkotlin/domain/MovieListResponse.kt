package com.sachinsandbhor.retrofitkotlin.domain

/**
 * Created by Sachin.Sandbhor on 08-03-2018.
 */

data class MovieListResponse(
		val page: Int,
		val total_results: Int,
		val total_pages: Int,
		val results: List<Movie>
)
