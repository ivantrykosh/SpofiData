package com.ivantrykosh.app.spofidata.presentation.top_artists

import com.ivantrykosh.app.spofidata.domain.model.Artist

data class GetUserTopArtistsState(
    val isLoading: Boolean = false,
    val userTopArtists: List<Artist> = emptyList(),
    val error: Int? = null
)