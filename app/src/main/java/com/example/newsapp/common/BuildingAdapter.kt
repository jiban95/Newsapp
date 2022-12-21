package com.example.newsapp.common

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R

@BindingAdapter("urlToImage")
fun urlToImage(view: AppCompatImageView, url: String) {
    val options = RequestOptions.placeholderOf(R.drawable.placeholder).error(R.drawable.placeholder)
    Glide.with(view).setDefaultRequestOptions(options).load(url ?: "").into(view)
}
