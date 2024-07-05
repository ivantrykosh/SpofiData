package com.ivantrykosh.app.spofidata.data.remote

import com.ivantrykosh.app.spofidata.data.remote.dto.UserTopArtistsResponse
import com.ivantrykosh.app.spofidata.data.remote.dto.UserTopTracksResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UserApi {
    @GET("me/top/tracks")
    suspend fun getUserTopTracks(@Header("Authorization") token: String, @Query("time_range") timeRange: String, @Query("limit") limit: Int): UserTopTracksResponse // todo just for testing

    @GET("me/top/artists")
    suspend fun getUserTopArtists(@Header("Authorization") token: String, @Query("time_range") timeRange: String, @Query("limit") limit: Int): UserTopArtistsResponse // todo just for testing
}