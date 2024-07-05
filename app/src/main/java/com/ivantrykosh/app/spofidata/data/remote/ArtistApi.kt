package com.ivantrykosh.app.spofidata.data.remote

import com.ivantrykosh.app.spofidata.data.remote.dto.TracksDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ArtistApi {
    @GET("artists/{id}/top-tracks")
    suspend fun getArtistTopTracks(@Header("Authorization") token: String, @Path("id") artistId: String): TracksDto // todo just for testing
}