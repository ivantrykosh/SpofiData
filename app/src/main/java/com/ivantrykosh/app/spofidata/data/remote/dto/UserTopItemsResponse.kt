package com.ivantrykosh.app.spofidata.data.remote.dto

data class UserTopTracksResponse(
    val items: List<TrackDto>
)

data class UserTopArtistsResponse(
    val items: List<ArtistDto>
)