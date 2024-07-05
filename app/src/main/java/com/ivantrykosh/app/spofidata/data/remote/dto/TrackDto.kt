package com.ivantrykosh.app.spofidata.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TrackDto(
    val id: String,
    val uri: String,
    val name: String,
    val album: AlbumDto,
    val artists: List<ArtistDto>,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrl,
)

data class TracksDto(
    val tracks: List<TrackDto>
)
