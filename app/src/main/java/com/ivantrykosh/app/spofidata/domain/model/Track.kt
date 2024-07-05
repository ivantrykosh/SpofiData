package com.ivantrykosh.app.spofidata.domain.model

import com.ivantrykosh.app.spofidata.data.remote.dto.TrackDto

data class Track(
    val id: String,
    val uri: String,
    val name: String,
    val album: Album,
    val artists: List<Artist>,
    val spotifyUrl: String,
)

fun TrackDto.toTrack(): Track {
    return Track(
        id = id,
        uri = uri,
        name = name,
        album = album.toAlbum(),
        artists = artists.map { it.toArtist() },
        spotifyUrl = externalUrls.spotify,
    )
}
