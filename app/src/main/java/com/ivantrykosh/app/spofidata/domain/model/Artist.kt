package com.ivantrykosh.app.spofidata.domain.model

import com.ivantrykosh.app.spofidata.data.remote.dto.ArtistDto

data class Artist(
    val id: String,
    val uri: String,
    val name: String,
    val spotifyUrl: String,
    val imageUrl: String,
)

fun ArtistDto.toArtist(): Artist {
    return Artist(
        id = id,
        uri = uri,
        name = name,
        spotifyUrl = externalUrls.spotify,
        imageUrl = images?.first()?.url ?: "",
    )
}