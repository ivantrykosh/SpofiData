package com.ivantrykosh.app.spofidata.presentation.top_tracks

import com.ivantrykosh.app.spofidata.domain.model.Track

data class GetArtistTopTracksState(
    val isLoading: Boolean = false,
    val artistTopTracks: List<Track> = emptyList(),
    val error: Int? = null
)
