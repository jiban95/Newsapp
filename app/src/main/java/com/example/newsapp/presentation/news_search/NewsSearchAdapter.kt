package com.example.newsapp.presentation.news_search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R
import com.example.newsapp.domain.model.News
import com.example.newsapp.presentation.news_list.NewsVerticalListAdapter

class NewsSearchAdapter(private val list: List<News>,private val listener: NewsVerticalListAdapter.NewsClickListener) :
    RecyclerView.Adapter<NewsSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vertical_news_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsModel = list[position]
        // sets the text to the textview from our itemHolder class

        val options =
            RequestOptions.placeholderOf(R.drawable.placeholder).error(R.drawable.placeholder)
        Glide.with(holder.image).setDefaultRequestOptions(options)
            .load(newsModel.urlToImage).into(holder.image)
        holder.tvHeading.text = newsModel.title
        holder.parentView.setOnClickListener {
            listener.onNewsItemClick(position+1,newsModel)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image: AppCompatImageView = itemView.findViewById(R.id.image)
        val tvHeading: AppCompatTextView = itemView.findViewById(R.id.tvHeading)
        val parentView: ConstraintLayout = itemView.findViewById(R.id.parentView)
    }

    interface NewsClickListener {
        fun onClickItem(id: Int, itemsViewModel: News)
    }
}