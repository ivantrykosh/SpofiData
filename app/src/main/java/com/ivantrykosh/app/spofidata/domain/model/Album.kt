package com.ivantrykosh.app.spofidata.domain.model

import com.ivantrykosh.app.spofidata.data.remote.dto.AlbumDto

data class Album(
    val id: String,
    val uri: String,
    val name: String,
    val spotifyUrl: String,
    val imageUrl: String,
    val artists: List<Artist>,
)

fun AlbumDto.toAlbum(): Album {
    return Album(
        id = id,
        uri = uri,
        name = name,
        spotifyUrl = externalUrls.spotify,
        imageUrl = images.first().url,
        artists = artists.map { it.toArtist() }
    )
}
