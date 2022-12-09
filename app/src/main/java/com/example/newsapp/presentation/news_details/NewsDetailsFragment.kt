package com.example.newsapp.presentation.news_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsDetailsBinding
import com.example.newsapp.presentation.news_db_operation.NewsDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {
    private val newsDatabaseViewModel: NewsDatabaseViewModel by viewModels()
    private var _binding: FragmentNewsDetailsBinding? = null
    var ids: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.toolbar?.setNavigationIcon(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_back
            )
        )
        ids = arguments?.getInt("id")

        ids?.let {
            lifecycle.coroutineScope.launchWhenCreated {
                val data = newsDatabaseViewModel.getNewsBookMark(it)
                with(data) {
                    _binding!!.tvTitle.text = title
                    _binding!!.dateTime.text = publishedAt
                    _binding!!.tvDescription.text = description
                    _binding!!.tvDescription.text = description
                    val options =
                        RequestOptions.placeholderOf(R.drawable.placeholder).error(R.drawable.error)
                    Glide.with(_binding!!.image).setDefaultRequestOptions(options)
                        .load(urlToImage).into(_binding!!.image)
                }
            }
        }
    }
}