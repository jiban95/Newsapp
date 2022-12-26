package com.example.newsapp.presentation.news_search

import android.content.SearchRecentSuggestionsProvider

/**
 * Class to provide search suggestion info
 */
class SearchSuggestionInfo : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.example.newsapp.presentation.news_search.SearchSuggestionInfo"
        const val MODE: Int = DATABASE_MODE_QUERIES
    }
}