package com.ivantrykosh.app.spofidata.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ivantrykosh.app.spofidata.presentation.top_artists.TopArtistsScreen
import com.ivantrykosh.app.spofidata.presentation.top_tracks.TopTracksScreen

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.BottomScreen.TopTracksScreen.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = Screen.BottomScreen.TopTracksScreen.route) {
            TopTracksScreen(navController = navController)
        }
        composable(route = Screen.BottomScreen.TopArtistsScreen.route) {
            TopArtistsScreen(navController = navController)
        }
    }
}