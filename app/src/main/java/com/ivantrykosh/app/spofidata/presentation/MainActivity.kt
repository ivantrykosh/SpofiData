package com.ivantrykosh.app.spofidata.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ivantrykosh.app.spofidata.common.SpotifyManager
import com.ivantrykosh.app.spofidata.common.SpotifyManager.spotifyAppRemote
import com.ivantrykosh.app.spofidata.ui.theme.SpofiDataTheme
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val requestCode = 1337

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSpotifyToken()
        connectSpotifyAppRemote()
        setContent()
    }

    private fun getSpotifyToken() {
        val builder = AuthorizationRequest.Builder(SpotifyManager.CLIENT_ID, AuthorizationResponse.Type.TOKEN, SpotifyManager.REDIRECT_URI)
        builder.setScopes(listOf("app-remote-control", "user-top-read").toTypedArray())
        val request = builder.build()
        AuthorizationClient.openLoginActivity(this, requestCode, request)
    }

    private fun connectSpotifyAppRemote() {
        val connectionParams = ConnectionParams.Builder(SpotifyManager.CLIENT_ID)
            .setRedirectUri(SpotifyManager.REDIRECT_URI)
            .showAuthView(true)
            .build()

        spotifyAppRemote?.let {
            SpotifyAppRemote.disconnect(it) // fixme: connection still exists (upd: connection is killed after 5 minutes)
        }

        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
            override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                SpotifyManager.setSpotifyAppRemote(spotifyAppRemote)
            }

            override fun onFailure(throwable: Throwable) {
                Toast.makeText(this@MainActivity, throwable.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == this.requestCode) {
            val response = AuthorizationClient.getResponse(resultCode, data)
            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {
                    SpotifyManager.setSpotifyToken(response.accessToken)
//                    setContent() // todo make call this function in onCreate
                }
                AuthorizationResponse.Type.ERROR -> Toast.makeText(this, response.error, Toast.LENGTH_SHORT).show()
                AuthorizationResponse.Type.CODE -> Toast.makeText(this, "Code: " + response.code, Toast.LENGTH_SHORT).show()
                AuthorizationResponse.Type.EMPTY -> Toast.makeText(this, "Empty: $response", Toast.LENGTH_SHORT).show()
                AuthorizationResponse.Type.UNKNOWN -> Toast.makeText(this, "Unknown: $response", Toast.LENGTH_SHORT).show()
                null -> Toast.makeText(this, "Null error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setContent() {
        setContent {
            SpofiDataTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        spotifyAppRemote?.let {
            SpotifyAppRemote.disconnect(it) // fixme: connection still exists (upd: connection is killed after 5 minutes)
        }
        AuthorizationClient.clearCookies(this)
    }
}