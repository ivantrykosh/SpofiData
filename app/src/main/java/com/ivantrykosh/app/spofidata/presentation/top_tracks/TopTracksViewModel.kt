package com.ivantrykosh.app.spofidata.presentation.top_tracks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivantrykosh.app.spofidata.common.Resource
import com.ivantrykosh.app.spofidata.domain.use_case.artist.GetArtistTopTracksUseCase
import com.ivantrykosh.app.spofidata.domain.use_case.user.GetUserTopTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TopTracksViewModel @Inject constructor(
    private val getArtistTopTracksUseCase: GetArtistTopTracksUseCase,
    private val getUserTopTracksUseCase: GetUserTopTracksUseCase,
) : ViewModel() {

    private var _getArtistTopTrackState by mutableStateOf(GetArtistTopTracksState())
    val getArtistTopTracksState get() = _getArtistTopTrackState

    private var _getUserTopTracksState by mutableStateOf(GetUserTopTracksState())
    val getUserTopTracksState get() = _getUserTopTracksState

    fun getArtistTopTracks(token: String, artistId: String) {
        _getArtistTopTrackState = GetArtistTopTracksState(isLoading = true)
        getArtistTopTracksUseCase(token, artistId).onEach { result ->
            _getArtistTopTrackState = when (result) {
                is Resource.Success -> GetArtistTopTracksState(isLoading = false, artistTopTracks = result.data ?: emptyList())
                is Resource.Error -> GetArtistTopTracksState(isLoading = false, error = result.statusCode)
                is Resource.Loading -> GetArtistTopTracksState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }

    fun getUserTopTracks(token: String, timeRange: String, limit: Int) {
        _getUserTopTracksState = GetUserTopTracksState(isLoading = true)
        getUserTopTracksUseCase(token, timeRange, limit).onEach { result ->
            _getUserTopTracksState = when (result) {
                is Resource.Success -> GetUserTopTracksState(isLoading = false, userTopTracks = result.data ?: emptyList())
                is Resource.Error -> GetUserTopTracksState(isLoading = false, error = result.statusCode)
                is Resource.Loading -> GetUserTopTracksState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }
}