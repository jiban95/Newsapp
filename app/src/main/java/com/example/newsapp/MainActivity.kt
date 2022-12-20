package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.presentation.news_bookmark.NewsBookmarkListFragment
import com.example.newsapp.presentation.news_list.NewsListFragment
import com.example.newsapp.presentation.news_search.NewsSearchActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsListFragment())
            .commit()

        binding.navView.setOnItemSelectedListener {
            var fragment: Fragment? = null

            when (it.itemId) {
                R.id.home -> {
                    fragment = NewsListFragment()
                }
                R.id.bookmark -> {
                    fragment = NewsBookmarkListFragment()
                }
                R.id.search -> {
                    startActivity(Intent(this, NewsSearchActivity::class.java))
                }
            }

            loadFragment(fragment)
        }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        fragment?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
        return true
    }
}