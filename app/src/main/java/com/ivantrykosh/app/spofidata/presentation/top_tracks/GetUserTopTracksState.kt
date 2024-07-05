package com.ivantrykosh.app.spofidata.presentation.top_tracks

import com.ivantrykosh.app.spofidata.domain.model.Track

data class GetUserTopTracksState(
    val isLoading: Boolean = false,
    val userTopTracks: List<Track> = emptyList(),
    val error: Int? = null
)
