package com.ivantrykosh.app.spofidata.common

import com.ivantrykosh.app.spofidata.BuildConfig
import com.spotify.android.appremote.api.SpotifyAppRemote

object SpotifyManager {
    const val CLIENT_ID = BuildConfig.CLIENT_ID
    const val REDIRECT_URI = "spofi-data-login://callback"

    private var _spotifyAppRemote: SpotifyAppRemote? = null
    val spotifyAppRemote get() = _spotifyAppRemote

    private var _spotifyToken: String? = null
    val spotifyToken get() =  _spotifyToken

    fun setSpotifyAppRemote(spotifyAppRemote: SpotifyAppRemote) {
        _spotifyAppRemote = spotifyAppRemote
    }

    fun setSpotifyToken(spotifyToken: String) {
        println("token is set")
        _spotifyToken = spotifyToken
    }
}