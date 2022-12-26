package com.example.newsapp.presentation.news_bookmark

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsbookmarkListBinding
import com.example.newsapp.presentation.news_db_operation.NewsDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsBookmarkListFragment : Fragment() {
    private val newsDatabaseViewModel: NewsDatabaseViewModel by viewModels()
    private var _binding: FragmentNewsbookmarkListBinding? = null
    lateinit var searchView: SearchView

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

        val menuHost: MenuHost = requireActivity()
        _binding!!.newsRecycler.layoutManager = GridLayoutManager(context, 2)

        lifecycle.coroutineScope.launchWhenCreated {

            val newsBookmarkData = newsDatabaseViewModel.getNewsBookMark()
            if (newsBookmarkData.isNotEmpty()) {
                val newsBookmarkAdapter = NewsBookmarkAdapter(newsBookmarkData)
                _binding!!.newsRecycler.adapter = newsBookmarkAdapter
            } else {
                _binding!!.imgNoRecord.visibility = View.VISIBLE
                _binding!!.tvNoDataFound.visibility = View.VISIBLE
            }
        }
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.search_menu, menu)
                val searchItem = menu.findItem(R.id.search)
                searchView = searchItem.actionView as SearchView
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.search -> {
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}