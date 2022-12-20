package com.example.newsapp.presentation.news_search

import android.content.SearchRecentSuggestionsProvider

class SearchSuggestionInfo : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.example.newsapp.presentation.news_search.SearchSuggestionInfo"
        const val MODE: Int = DATABASE_MODE_QUERIES
    }
}