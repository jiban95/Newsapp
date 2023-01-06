package com.example.newsapp.presentation.news_bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsapp.databinding.FragmentNewsbookmarkListBinding
import com.example.newsapp.domain.model.News
import com.example.newsapp.presentation.news_db_operation.NewsDatabaseViewModel
import com.example.newsapp.presentation.news_details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment class to show news bookmark from room database
 */
@AndroidEntryPoint
class NewsBookmarkListFragment : Fragment(), NewsBookmarkAdapter.NewsClickListener {
    private val newsDatabaseViewModel: NewsDatabaseViewModel by viewModels()
    private var _binding: FragmentNewsbookmarkListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsbookmarkListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(_binding!!.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)


        _binding!!.newsRecycler.layoutManager = GridLayoutManager(context, 2)

        lifecycle.coroutineScope.launchWhenCreated {

            val newsBookmarkData =
                newsDatabaseViewModel.getNewsBookMark() // method to get news bookmark from NewsDatabaseViewModel
            if (newsBookmarkData.isNotEmpty()) {
                val newsBookmarkAdapter =
                    NewsBookmarkAdapter(newsBookmarkData, this@NewsBookmarkListFragment)
                _binding!!.newsRecycler.adapter = newsBookmarkAdapter
            } else {
                _binding!!.imgNoRecord.visibility = View.VISIBLE
                _binding!!.tvNoDataFound.visibility = View.VISIBLE
            }
        }
    }

    override fun onNewsItemClick(id: Int, data: News) {
        startActivity(
            Intent(activity, DetailsActivity::class.java).putExtra("id", id)
                .putExtra("news", data)
        )
    }
}