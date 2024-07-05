package com.ivantrykosh.app.spofidata.presentation.top_artists

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ivantrykosh.app.spofidata.R
import com.ivantrykosh.app.spofidata.common.Constants
import com.ivantrykosh.app.spofidata.common.Constants.terms
import com.ivantrykosh.app.spofidata.common.SpotifyManager.spotifyToken
import com.ivantrykosh.app.spofidata.domain.model.Artist
import com.ivantrykosh.app.spofidata.ui.theme.Green
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopArtistsScreen(
    navController: NavController, // todo maybe delete this parameter (it's useless)
    topArtistsViewModel: TopArtistsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val pagerState = rememberPagerState { terms.size }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = spotifyToken, key2 = pagerState.currentPage) {
        if (spotifyToken != null) {
            topArtistsViewModel.getUserTopArtists(spotifyToken!!, terms[pagerState.currentPage], 20)
        }
    }

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            terms.forEachIndexed { index, term ->
                Tab(selected = pagerState.currentPage == index, onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                        topArtistsViewModel.getUserTopArtists(spotifyToken!!, term, 20)
                    }
                }) {
                    Text(text = stringResource(id = Constants.adaptiveTerms[term]!!), modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
        HorizontalPager(state = pagerState) { page ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)) {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp))
                when {
                    topArtistsViewModel.getUserTopArtistsState.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    topArtistsViewModel.getUserTopArtistsState.error != null -> {
                        Text(text = "AN ERROR OCCURRED", color = MaterialTheme.colorScheme.error, modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .padding(8.dp)) // TODO change error text
                    }
                    topArtistsViewModel.getUserTopArtistsState.userTopArtists.isEmpty() -> {
                        Text(text = stringResource(id = R.string.no_user_top_artists), modifier = Modifier.align(
                            Alignment.TopCenter))
                    }
                    else -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(topArtistsViewModel.getUserTopArtistsState.userTopArtists) { artists ->
                                ArtistItem(artist = artists) {
                                    val intent = Intent(Intent.ACTION_VIEW)
                                    intent.setData(Uri.parse(artists.spotifyUrl))
                                    context.startActivity(intent)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ArtistItem(artist: Artist, onItemClick: (Artist) -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .clickable { onItemClick(artist) }
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = artist.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxHeight()
            )
            Column(modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center) {
                Text(text = artist.name, overflow = TextOverflow.Ellipsis, maxLines = 1, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp))
    }
}