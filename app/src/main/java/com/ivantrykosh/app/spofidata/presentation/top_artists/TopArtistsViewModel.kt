package com.ivantrykosh.app.spofidata.presentation.top_artists

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivantrykosh.app.spofidata.common.Resource
import com.ivantrykosh.app.spofidata.domain.use_case.user.GetUserTopArtistsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TopArtistsViewModel @Inject constructor(
    private val getUserTopArtistsUseCase: GetUserTopArtistsUseCase,
) : ViewModel() {

    private var _getUserTopArtistsState by mutableStateOf(GetUserTopArtistsState())
    val getUserTopArtistsState get() = _getUserTopArtistsState

    fun getUserTopArtists(token: String, timeRange: String, limit: Int) {
        _getUserTopArtistsState = GetUserTopArtistsState(isLoading = true)
        getUserTopArtistsUseCase(token, timeRange, limit).onEach { result ->
            _getUserTopArtistsState = when (result) {
                is Resource.Success -> GetUserTopArtistsState(isLoading = false, userTopArtists = result.data ?: emptyList())
                is Resource.Error -> GetUserTopArtistsState(isLoading = false, error = result.statusCode)
                is Resource.Loading -> GetUserTopArtistsState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }
}