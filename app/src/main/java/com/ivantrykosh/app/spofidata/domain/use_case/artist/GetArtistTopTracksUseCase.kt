package com.ivantrykosh.app.spofidata.domain.use_case.artist

import com.ivantrykosh.app.spofidata.common.Resource
import com.ivantrykosh.app.spofidata.domain.model.Track
import com.ivantrykosh.app.spofidata.domain.model.toTrack
import com.ivantrykosh.app.spofidata.domain.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetArtistTopTracksUseCase @Inject constructor(
    private val repository: ArtistRepository,
) {
    operator fun invoke(token: String, artistId: String): Flow<Resource<List<Track>>> = flow {
        try {
            emit(Resource.Loading())
            val topTracks = repository.getArtistTopTracks("Bearer $token", artistId).tracks.map { it.toTrack() }
            emit(Resource.Success(topTracks))
        } catch (e: HttpException) {
            emit(Resource.Error(e.code()))
        } catch (e: Exception) {
            emit(Resource.Error(-1))
        }
    }
}