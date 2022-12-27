package com.example.newsapp.presentation.news_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.domain.model.News

/**
 * Adapter class to show news card vertically
 */
class NewsHeadingAdapter(private val list: List<News>) :
    RecyclerView.Adapter<NewsHeadingAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = list[position]
        // sets the data to the textview from our itemHolder class
        holder.tvHeading.text = ItemsViewModel.title
        holder.tvDateTime.text = ItemsViewModel.publishedAt
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvHeading: AppCompatTextView = itemView.findViewById(R.id.tvHeading)
        val tvDateTime: AppCompatTextView = itemView.findViewById(R.id.tvDateTime)
    }
}

