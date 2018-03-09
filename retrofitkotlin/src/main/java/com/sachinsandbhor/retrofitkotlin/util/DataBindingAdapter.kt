package com.sachinsandbhor.retrofitkotlin.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by Sachin.Sandbhor on 08-03-2018.
 */
object DataBindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        Picasso.with(view.context)
                .load(url)
                .into(view)
    }
}