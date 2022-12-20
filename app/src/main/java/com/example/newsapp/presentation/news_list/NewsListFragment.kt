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
import com.example.newsapp.common.AppCommon.LoadingDialog
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.domain.model.News
import com.example.newsapp.presentation.news_details.DetailsActivity
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment(), NewsVerticalListAdapter.ItemClickListener {
    private val newsListViewModel: NewsListViewModel by viewModels()
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

        _binding!!.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        callNewsData()
                    }
                    1 -> {
                        callEventsData()
                    }
                    2 -> {
                        callWeatherData()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        _binding!!.horizontalRecycler.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        _binding!!.verticalRecycler.layoutManager = GridLayoutManager(
            context,
            2
        )
        callNewsData() // Calling news api to get news info
    }

    override fun onClickItem(id: Int, newsInfo: News) {
        startActivity(
            Intent(activity, DetailsActivity::class.java).putExtra("id", id)
                .putExtra("news", newsInfo)
        )
    }

    private fun callNewsData() {
        val dialog = LoadingDialog(requireActivity())
        dialog.startLoading()
        if (context?.let { CommonFile.isOnline(it) } == true) {
            newsListViewModel.getNewsData(1)

            lifecycle.coroutineScope.launchWhenCreated {
                newsListViewModel.newsList.collect {
                    if (it.data?.isNotEmpty() == true) {
                        val newsHeadingAdapter = NewsHeadingAdapter(it.data)
                        val newsVerticalAdapter =
                            NewsVerticalListAdapter(it.data, this@NewsListFragment)
                        _binding!!.horizontalRecycler.adapter = newsHeadingAdapter
                        _binding!!.verticalRecycler.adapter = newsVerticalAdapter

                        _binding!!.imgNoRecord.visibility = View.INVISIBLE
                        _binding!!.tvNoDataFound.visibility = View.INVISIBLE
                        _binding!!.layoutContainer.visibility = View.VISIBLE
                    } else {
                        _binding!!.layoutContainer.visibility = View.INVISIBLE
                        _binding!!.imgNoRecord.visibility = View.VISIBLE
                        _binding!!.tvNoDataFound.visibility = View.VISIBLE
                        if (it.error.isNotEmpty()) {
                            Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                        }
                    }
                    dialog.dismiss()
                }
            }
        } else {
            _binding!!.layoutContainer.visibility = View.INVISIBLE
            _binding!!.imgNoRecord.visibility = View.VISIBLE
            _binding!!.tvNoDataFound.visibility = View.VISIBLE
             dialog.dismiss()
            Toast.makeText(context, "No internet available", Toast.LENGTH_LONG).show()
        }
    }

    private fun callEventsData() {
        val dialog = LoadingDialog(requireActivity())
        if (context?.let { CommonFile.isOnline(it) } == true) {
            newsListViewModel.getEventData(2)
            dialog.startLoading()

            lifecycle.coroutineScope.launchWhenCreated {
                newsListViewModel.newsList.collect {
                    if (it.data?.isNotEmpty() == true) {
                        val newsHeadingAdapter = NewsHeadingAdapter(it.data)
                        val newsVerticalAdapter =
                            NewsVerticalListAdapter(it.data, this@NewsListFragment)
                        _binding!!.horizontalRecycler.adapter = newsHeadingAdapter
                        _binding!!.verticalRecycler.adapter = newsVerticalAdapter

                        dialog.dismiss()
                        _binding!!.imgNoRecord.visibility = View.INVISIBLE
                        _binding!!.tvNoDataFound.visibility = View.INVISIBLE
                        _binding!!.layoutContainer.visibility = View.VISIBLE
                    } else {
                        dialog.dismiss()
                        _binding!!.layoutContainer.visibility = View.INVISIBLE
                        _binding!!.imgNoRecord.visibility = View.VISIBLE
                        _binding!!.tvNoDataFound.visibility = View.VISIBLE
                        if (it.error.isNotEmpty()) {
                            Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        } else {
            dialog.dismiss()
            _binding!!.layoutContainer.visibility = View.INVISIBLE
            _binding!!.imgNoRecord.visibility = View.VISIBLE
            _binding!!.tvNoDataFound.visibility = View.VISIBLE
            Toast.makeText(context, "No internet available", Toast.LENGTH_LONG).show()
        }
    }

    private fun callWeatherData() {
        val dialog = LoadingDialog(requireActivity())
        if (context?.let { CommonFile.isOnline(it) } == true) {
            newsListViewModel.getWeatherData(3)
            dialog.startLoading()

            lifecycle.coroutineScope.launchWhenCreated {
                newsListViewModel.newsList.collect {
                    if (it.data?.isNotEmpty() == true) {
                        val newsHeadingAdapter = NewsHeadingAdapter(it.data)
                        val newsVerticalAdapter =
                            NewsVerticalListAdapter(it.data, this@NewsListFragment)
                        _binding!!.horizontalRecycler.adapter = newsHeadingAdapter
                        _binding!!.verticalRecycler.adapter = newsVerticalAdapter

                        dialog.dismiss()
                        _binding!!.imgNoRecord.visibility = View.INVISIBLE
                        _binding!!.tvNoDataFound.visibility = View.INVISIBLE
                        _binding!!.layoutContainer.visibility = View.VISIBLE
                    } else {
                        dialog.dismiss()
                        _binding!!.layoutContainer.visibility = View.INVISIBLE
                        _binding!!.imgNoRecord.visibility = View.VISIBLE
                        _binding!!.tvNoDataFound.visibility = View.VISIBLE
                        if (it.error.isNotEmpty()) {
                            Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        } else {
            dialog.dismiss()
            _binding!!.layoutContainer.visibility = View.INVISIBLE
            _binding!!.imgNoRecord.visibility = View.VISIBLE
            _binding!!.tvNoDataFound.visibility = View.VISIBLE
            Toast.makeText(context, "No internet available", Toast.LENGTH_LONG).show()
        }
    }
}