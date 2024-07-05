package com.ivantrykosh.app.spofidata.data.repository

import com.ivantrykosh.app.spofidata.data.remote.ArtistApi
import com.ivantrykosh.app.spofidata.data.remote.dto.TracksDto
import com.ivantrykosh.app.spofidata.domain.repository.ArtistRepository
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val artistApi: ArtistApi,
) : ArtistRepository {
    override suspend fun getArtistTopTracks(token: String, artistId: String): TracksDto {
        return artistApi.getArtistTopTracks(token, artistId)
    }
}