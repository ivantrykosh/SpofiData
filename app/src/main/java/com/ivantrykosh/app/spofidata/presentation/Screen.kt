package com.ivantrykosh.app.spofidata.presentation

import androidx.annotation.DrawableRes
import com.ivantrykosh.app.spofidata.R

sealed class Screen(val route: String, val title: String) {
    sealed class BottomScreen(route: String, title: String, @DrawableRes val icon: Int): Screen(route, title) {
        data object TopTracksScreen: BottomScreen("top_tracks_screen", "Top Tracks", R.drawable.baseline_audiotrack_24)
        data object TopArtistsScreen: BottomScreen("top_artists_screen", "Top Artists", R.drawable.baseline_people_24)
    }
}

val screensInBottom = listOf(
    Screen.BottomScreen.TopTracksScreen,
    Screen.BottomScreen.TopArtistsScreen,
)