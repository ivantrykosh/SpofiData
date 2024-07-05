package com.ivantrykosh.app.spofidata.data.repository

import com.ivantrykosh.app.spofidata.data.remote.UserApi
import com.ivantrykosh.app.spofidata.data.remote.dto.UserTopArtistsResponse
import com.ivantrykosh.app.spofidata.data.remote.dto.UserTopTracksResponse
import com.ivantrykosh.app.spofidata.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
) : UserRepository {
    override suspend fun getUserTopTracks(token: String, timeRange: String, limit: Int): UserTopTracksResponse {
        return userApi.getUserTopTracks(token, timeRange, limit)
    }

    override suspend fun getUserTopArtists(token: String, timeRange: String, limit: Int): UserTopArtistsResponse {
        return userApi.getUserTopArtists(token, timeRange, limit)
    }
}