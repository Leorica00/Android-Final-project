package com.example.finalproject.presentation.screen.wallpapers

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.finalproject.databinding.FragmentWallpaperBinding
import com.example.finalproject.data.common.AppError
import com.example.finalproject.presentation.base.BaseFragment
import com.example.finalproject.presentation.event.WallpapersEvent
import com.example.finalproject.presentation.extension.showSnackBar
import com.example.finalproject.presentation.screen.wallpapers.adapter.WallpapersRecyclerViewAdapter
import com.example.finalproject.presentation.screen.wallpapers.listener.OnWallpaperClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WallpaperFragment : BaseFragment<FragmentWallpaperBinding>(FragmentWallpaperBinding::inflate) {

    private val viewModel: WallpaperViewModel by viewModels()
    private val wallpaperRecyclerViewAdapter = WallpapersRecyclerViewAdapter()

    override fun setUp() {
        with(binding.recyclerWallpapers) {
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
            adapter = wallpaperRecyclerViewAdapter
        }
    }

    private fun searchData(): String {
        return binding.etSearch.text.toString().replace("\\s+".toRegex(), "+")
    }

    override fun setUpListeners() {
        binding.btnSearch.setOnClickListener {
            wallpaperRecyclerViewAdapter.refresh()
        }
    }

    override fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pagingDataFlow.collectLatest { pagingData ->
                    wallpaperRecyclerViewAdapter.submitData(pagingData)
                }
            }
        }
        handlePagingState()
    }

    private fun handlePagingState() {
        wallpaperRecyclerViewAdapter.addLoadStateListener { loadStates ->
            when (loadStates.refresh) {
                is LoadState.Error -> {
                    val error: AppError = AppError.fromException((loadStates.refresh as LoadState.Error).error)
                    binding.progressBarWallpaper.visibility = View.GONE
                    binding.root.showSnackBar(error.message)
                }

                else -> {
                    binding.progressBarWallpaper.isVisible = loadStates.refresh is LoadState.Loading
                }
            }
        }
    }
}