package com.example.aqwastask.framework.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import com.example.aqwastask.R
import com.example.aqwastask.business.entities.Item
import com.example.aqwastask.business.utils.DataState.*
import com.example.aqwastask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    @Inject
    lateinit var repoAdapter: RepoAdapter
    lateinit var popupMenu: PopupMenu
    private var trendingRepos: MutableList<Item>? = null


    private val viewModel: TrendingReposViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initiateUI()
        subscribeOnObservers()
    }

    private fun subscribeOnObservers() {
        viewModel.reposDataState.observe(this, {
            when (it) {
                is Success -> {
                    trendingRepos = it.data.items.toMutableList()
                    repoAdapter.submitList(it.data.items)
                    binding.swipeToRefreshLayout.isRefreshing = false
                    binding.failed = it.data.items.isEmpty()
                }
                is Loading -> binding.isLoading = it.isLoading
                is Failure -> {
                    binding.swipeToRefreshLayout.isRefreshing = false
                    binding.failed = true
                }
            }
        })
    }

    private fun initiateUI() {
        popupMenu = PopupMenu(this, binding.icMore)
        popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
        binding.reposRV.adapter = repoAdapter
        binding.swipeToRefreshLayout.setOnRefreshListener {
            binding.isLoading = true
            viewModel.getTrendingRepos()
        }
        binding.icMore.setOnClickListener {
            showMenu()
        }
        binding.retryBtn.setOnClickListener {
            viewModel.getTrendingRepos()
        }
    }

    private fun showMenu() {
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.title) {
                getString(R.string.sort_by_name) -> repoAdapter.submitList(trendingRepos?.sortedByDescending { it.repo })
                getString(R.string.sort_by_stars) -> repoAdapter.submitList(trendingRepos?.sortedBy { it.stars })
            }
            true
        }
        popupMenu.show()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}