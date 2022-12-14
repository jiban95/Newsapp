package com.example.newsapp.presentation.news_bookmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R
import com.example.newsapp.domain.model.NewsBookMark

class NewsBookmarkAdapter(private val newsBookmarkList: List<NewsBookMark>) :
    RecyclerView.Adapter<NewsBookmarkAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image: AppCompatImageView = itemView.findViewById(R.id.image)
        val tvHeading: AppCompatTextView = itemView.findViewById(R.id.tvHeading)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vertical_news_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(newsBookmarkList[position]) {
            val options =
                RequestOptions.placeholderOf(R.drawable.placeholder).error(R.drawable.placeholder)
            Glide.with(holder.image).setDefaultRequestOptions(options)
                .load(urlToImage ?: "").into(holder.image)
            holder.tvHeading.text = title
        }
    }

    override fun getItemCount(): Int {
        return newsBookmarkList.size
    }
}
