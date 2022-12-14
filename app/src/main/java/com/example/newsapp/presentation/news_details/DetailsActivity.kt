package com.example.newsapp.presentation.news_details

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.coroutineScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityDetailsBinding
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.NewsBookMark

import com.example.newsapp.presentation.news_db_operation.NewsDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private val newsDatabaseViewModel: NewsDatabaseViewModel by viewModels()
    lateinit var _binding: ActivityDetailsBinding
    var ids: Int? = null
    lateinit var newsInfo: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        setSupportActionBar(_binding.toolbar)

        _binding?.toolbar?.setNavigationIcon(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_back
            )
        )
        _binding.toolbar.overflowIcon?.setTint(Color.BLACK)
        _binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        invalidateOptionsMenu()

        ids = intent.getIntExtra("id", 0)
        newsInfo = intent.getParcelableExtra("news")!!

        newsInfo?.let {
            with(newsInfo) {
                _binding!!.tvTitle.text = title
                _binding!!.dateTime.text = publishedAt.substringBefore("+")
                _binding!!.tvDescription.text = description
                val options =
                    RequestOptions.placeholderOf(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                Glide.with(_binding!!.image).setDefaultRequestOptions(options)
                    .load(urlToImage).into(_binding!!.image)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_page_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.turned -> {
                item.setIcon(R.drawable.ic_turned_in)
                saveBookMark()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun saveBookMark() {
        lifecycle.coroutineScope.launchWhenCreated {
            val newsData = NewsBookMark()
            with(newsInfo) {
                newsData.id = ids!!
                newsData.title = title
                newsData.description = description
                newsData.url = url
                newsData.urlToImage = urlToImage
                newsData.publishedAt = publishedAt
                newsData.content = content
            }
            newsDatabaseViewModel.insertNewsData(newsData)
        }
    }
}