package com.example.newsapp.presentation.news_search

import android.app.SearchManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsapp.R
import com.example.newsapp.common.AppCommon.CommonFile
import com.example.newsapp.common.AppCommon.LoadingDialog
import com.example.newsapp.databinding.ActivityNewsSearchBinding
import com.example.newsapp.domain.model.News
import com.example.newsapp.presentation.news_details.DetailsActivity
import com.example.newsapp.presentation.news_list.NewsVerticalListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsSearchActivity : AppCompatActivity(), NewsVerticalListAdapter.NewsClickListener {
    private val newsSearchViewModel: NewsSearchViewModel by viewModels()
    private lateinit var binding: ActivityNewsSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.overflowIcon?.setTint(Color.WHITE)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.navigationIcon = ContextCompat.getDrawable(
            this,
            R.drawable.ic_back
        )

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.mNewsRecycler.layoutManager = GridLayoutManager(this, 2)

        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            val searchQuery = intent.getStringExtra(SearchManager.QUERY)
            getSearchData(searchQuery!!) // calling search api according to input
            SearchRecentSuggestions(
                this,
                SearchSuggestionInfo.AUTHORITY,
                SearchSuggestionInfo.MODE
            ).saveRecentQuery(searchQuery, null)
            SearchRecentSuggestions(
                this,
                SearchSuggestionInfo.AUTHORITY,
                SearchSuggestionInfo.MODE
            ).clearHistory()
        }
    }

    private fun getSearchData(searchQuery: String) {
        val dialog = LoadingDialog()
        if (CommonFile.isOnline(this)) {
            newsSearchViewModel.getNewsSearchData(searchQuery)

            lifecycle.coroutineScope.launchWhenCreated {
                newsSearchViewModel.newsList.collect { it ->
                    if (it.isLoading) {
                        dialog.startLoading(this@NewsSearchActivity)
                    }

                    if (it.error.isNotEmpty()) {
                        binding.mNewsRecycler.visibility = View.INVISIBLE
                        binding.imgNoRecord.visibility = View.VISIBLE
                        binding.tvNoDataFound.visibility = View.VISIBLE
                        Toast.makeText(this@NewsSearchActivity, it.error, Toast.LENGTH_LONG).show()
                    }

                    it.data?.let {
                        if (it.isNotEmpty()) {
                            val newsVerticalAdapter =
                                NewsVerticalListAdapter(it, this@NewsSearchActivity)
                            binding.mNewsRecycler.adapter = newsVerticalAdapter

                            binding.imgNoRecord.visibility = View.INVISIBLE
                            binding.tvNoDataFound.visibility = View.INVISIBLE
                            binding.mNewsRecycler.visibility = View.VISIBLE
                            dialog.dismiss()
                        } else {
                            binding.imgNoRecord.visibility = View.VISIBLE
                            binding.tvNoDataFound.visibility = View.VISIBLE
                            dialog.dismiss()
                        }
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isSubmitButtonEnabled = false
        return true
    }

    override fun onNewsItemClick(id: Int, newsModel: News) {
        startActivity(
            Intent(this@NewsSearchActivity, DetailsActivity::class.java).putExtra("id", id)
                .putExtra("news", newsModel)
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}