package com.ivantrykosh.app.spofidata.domain.repository

import com.ivantrykosh.app.spofidata.data.remote.dto.UserTopArtistsResponse
import com.ivantrykosh.app.spofidata.data.remote.dto.UserTopTracksResponse

interface UserRepository {
    suspend fun getUserTopTracks(token: String, timeRange: String, limit: Int): UserTopTracksResponse

    suspend fun getUserTopArtists(token: String, timeRange: String, limit: Int): UserTopArtistsResponse
}