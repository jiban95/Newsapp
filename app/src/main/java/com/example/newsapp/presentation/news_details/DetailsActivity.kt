package com.example.newsapp.presentation.news_details

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
import com.example.newsapp.presentation.news_db_operation.NewsDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private val newsDatabaseViewModel: NewsDatabaseViewModel by viewModels()
    lateinit var _binding: ActivityDetailsBinding
    var ids: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding?.toolbar?.setNavigationIcon(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_back
            )
        )
        ids = intent.getIntExtra("id",0)

        ids?.let {
            lifecycle.coroutineScope.launchWhenCreated {
                val data = newsDatabaseViewModel.getNewsBookMark(it)
                with(data) {
                    _binding!!.tvTitle.text = title
                    _binding!!.dateTime.text = publishedAt.substringBefore("+")
                    _binding!!.tvDescription.text = description
                    _binding!!.tvDescription.text = description
                    val options =
                        RequestOptions.placeholderOf(R.drawable.placeholder).error(R.drawable.error)
                    Glide.with(_binding!!.image).setDefaultRequestOptions(options)
                        .load(urlToImage).into(_binding!!.image)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_page_option_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}