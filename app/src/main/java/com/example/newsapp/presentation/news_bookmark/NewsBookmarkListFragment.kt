package com.example.newsapp.presentation.news_bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsapp.databinding.FragmentNewsbookmarkListBinding
import com.example.newsapp.presentation.news_db_operation.NewsDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsBookmarkListFragment : Fragment() {
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

        _binding!!.newsRecycler.layoutManager = GridLayoutManager(
            context,
            2
        )

        lifecycle.coroutineScope.launchWhenCreated {

            val newsBookmarkData = newsDatabaseViewModel.getNewsBookMark()
            if (newsBookmarkData != null && newsBookmarkData.isNotEmpty()) {
                val newsBookmarkAdapter = NewsBookmarkAdapter(newsBookmarkData)
                _binding!!.newsRecycler.adapter = newsBookmarkAdapter
            } else {
                _binding!!.imgNoRecord.visibility = View.VISIBLE
                _binding!!.tvNoDataFound.visibility = View.VISIBLE
            }
        }
    }
}