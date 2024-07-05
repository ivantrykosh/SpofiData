package com.ivantrykosh.app.spofidata.domain.repository

import com.ivantrykosh.app.spofidata.data.remote.dto.TracksDto

interface ArtistRepository {
    suspend fun getArtistTopTracks(token: String, artistId: String): TracksDto
}