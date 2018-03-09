package com.sachinsandbhor.retrofitkotlin.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.sachinsandbhor.retrofitkotlin.R
import com.squareup.picasso.Picasso

/**
 * Created by Sachin.Sandbhor on 08-03-2018.
 */
object DataBindingAdapter {
    val IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w342//"
    @JvmStatic
    @BindingAdapter("bind:imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        Picasso.with(view.context)
                .load(IMAGE_URL_BASE_PATH+url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view)
    }
}