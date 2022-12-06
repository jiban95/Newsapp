package com.example.newsapp.presentation.news_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.NewsBookMark
import com.example.newsapp.presentation.news_db_operation.NewsDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private val newsListViewModel: NewsListViewModel by viewModels()
    private val newsDatabaseViewModel: NewsDatabaseViewModel by viewModels()

    private var _binding: FragmentNewsListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding!!.horizontalRecycler.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        _binding!!.verticalRecycler.setLayoutManager(
            GridLayoutManager(
                context,
                2
            )
        )


        newsListViewModel.getNewsData()

        lifecycleScope.launch(Dispatchers.IO) {
            val data: List<News>? = newsListViewModel.newsList.value.data
            if (data != null) {

                for (i in data!!.indices) {
                    val news = NewsBookMark(
                        i,
                        data[i].title,
                        data[i].description,
                        data[i].url,
                        data[i].urlToImage,
                        data[i].publishedAt,
                        data[i].content
                    )
                      newsDatabaseViewModel.insertNewsData(news)
                }
            }
        }

        lifecycle.coroutineScope.launchWhenCreated {
            newsListViewModel.newsList.collect {
                if (it.isLoading) {
                    _binding!!.progressBar.display
                }

                if (it.data != null) {
                    val newsHeadingAdapter = NewsHeadingAdapter(it.data)
                    val newsVerticalAdapter = NewsVerticalListAdapter(it.data)
                    _binding!!.horizontalRecycler.adapter = newsHeadingAdapter
                    _binding!!.verticalRecycler.adapter = newsVerticalAdapter
                }
                _binding!!.progressBar.visibility = View.GONE
            }
        }
    }
}