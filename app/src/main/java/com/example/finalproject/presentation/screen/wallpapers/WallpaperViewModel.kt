package com.example.finalproject.presentation.screen.wallpapers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.finalproject.domain.usecase.wallpapers.GetWallpapersUseCase
import com.example.finalproject.presentation.event.WallpapersEvent
import com.example.finalproject.presentation.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val getWallpapersUseCase: GetWallpapersUseCase,
) : ViewModel() {

    private var _pagingDataFlow: MutableSharedFlow<PagingData<Image>> = MutableSharedFlow()
    val pagingDataFlow = _pagingDataFlow.cachedIn(viewModelScope)

    init {
        fetchUsersData()
    }

    fun onEvent(event: WallpapersEvent) {
        when (event) {
            is WallpapersEvent.FetchDefaultDataEvent -> fetchUsersData()
            }
    }

    private fun fetchUsersData() {
        viewModelScope.launch {
            getWallpapersUseCase().collectLatest {
                _pagingDataFlow.emit(it)
            }
        }
    }
}