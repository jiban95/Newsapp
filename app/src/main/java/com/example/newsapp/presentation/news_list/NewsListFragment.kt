package com.example.newsapp.presentation.news_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newsapp.common.AppCommon.CommonFile
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.domain.model.NewsBookMark
import com.example.newsapp.presentation.news_db_operation.NewsDatabaseViewModel
import com.example.newsapp.presentation.news_details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment(), NewsVerticalListAdapter.ItemClickListener {
    private val newsListViewModel: NewsListViewModel by viewModels()
    private val newsDatabaseViewModel: NewsDatabaseViewModel by viewModels()
    private var _binding: FragmentNewsListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding!!.horizontalRecycler.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        _binding!!.verticalRecycler.layoutManager = GridLayoutManager(
            context,
            2
        )

        if (context?.let { CommonFile.isOnline(it) } == true) {
            newsListViewModel.getNewsData()
            _binding!!.progressBar.visibility

            lifecycle.coroutineScope.launchWhenCreated {
                newsDatabaseViewModel.deleteNewsBookMark()
                newsListViewModel.newsList.collect {
                    if (it.data != null) {
                        val newsHeadingAdapter = NewsHeadingAdapter(it.data)
                        val newsVerticalAdapter =
                            NewsVerticalListAdapter(it.data, this@NewsListFragment)
                        _binding!!.horizontalRecycler.adapter = newsHeadingAdapter
                        _binding!!.verticalRecycler.adapter = newsVerticalAdapter

                        _binding!!.progressBar.visibility = View.INVISIBLE
                        _binding!!.imgNoRecord.visibility = View.INVISIBLE
                        _binding!!.tvNoDataFound.visibility = View.INVISIBLE
                        _binding!!.layoutContainer.visibility = View.VISIBLE

                        for (item in it.data) {
                            var newsData = NewsBookMark()
                            newsData.id = (it.data.indexOf(item)) + 1
                            newsData.title = item.title
                            newsData.description = item.description
                            newsData.url = item.url
                            newsData.urlToImage = item.urlToImage
                            newsData.publishedAt = item.publishedAt
                            newsData.content = item.content

                            newsDatabaseViewModel.insertNewsData(newsData)
                        }
                    } else {
                        _binding!!.progressBar.visibility = View.INVISIBLE
                        _binding!!.layoutContainer.visibility = View.INVISIBLE
                        _binding!!.imgNoRecord.visibility = View.VISIBLE
                        _binding!!.tvNoDataFound.visibility = View.VISIBLE
                        if (it.error.isNotEmpty())
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            _binding!!.progressBar.visibility = View.INVISIBLE
            _binding!!.layoutContainer.visibility = View.INVISIBLE
            _binding!!.imgNoRecord.visibility = View.VISIBLE
            _binding!!.tvNoDataFound.visibility = View.VISIBLE
            Toast.makeText(context, "No internet available", Toast.LENGTH_LONG).show()
        }
    }

    override fun onClickItem(id: Int) {
        startActivity(Intent(activity, DetailsActivity::class.java).putExtra("id",id))
    }
}