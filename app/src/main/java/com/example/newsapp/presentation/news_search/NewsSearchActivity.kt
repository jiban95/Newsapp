package com.example.newsapp.presentation.news_search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsSearchBinding

class NewsSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            Toast.makeText(this, intent.getStringExtra(SearchManager.QUERY), Toast.LENGTH_LONG)
                .show()

            val searchQuery = intent.getStringExtra(SearchManager.QUERY)

            SearchRecentSuggestions(
                this,
                SearchSuggestionInfo.AUTHORITY,
                SearchSuggestionInfo.MODE
            ).saveRecentQuery(searchQuery, null)
            SearchRecentSuggestions(this, SearchSuggestionInfo.AUTHORITY, SearchSuggestionInfo.MODE)
                .clearHistory()
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
}