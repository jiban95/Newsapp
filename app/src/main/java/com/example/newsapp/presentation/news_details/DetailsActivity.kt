package com.example.newsapp.presentation.news_details

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
    private lateinit var _binding: ActivityDetailsBinding
    private var ids: Int? = null
    private lateinit var newsInfo: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        setSupportActionBar(_binding.toolbar)

        _binding.toolbar.navigationIcon = ContextCompat.getDrawable(
            this,
            R.drawable.ic_back
        )
        _binding.toolbar.overflowIcon?.setTint(Color.WHITE)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        _binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        invalidateOptionsMenu()

        ids = intent.getIntExtra("id", 0)
        newsInfo = intent.getParcelableExtra("news")!!

        with(newsInfo) {
            _binding.tvTitle.text = title
            _binding.dateTime.text = publishedAt.substringBefore("+")
            _binding.tvDescription.text = description
            val options =
                RequestOptions.placeholderOf(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
            Glide.with(_binding.image).setDefaultRequestOptions(options)
                .load(urlToImage).into(_binding.image)
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

            R.id.share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, newsInfo.url)
                startActivity(Intent.createChooser(shareIntent, "Share via"))
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