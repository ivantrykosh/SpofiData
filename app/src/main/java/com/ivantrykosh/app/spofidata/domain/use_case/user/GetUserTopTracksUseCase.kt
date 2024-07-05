package com.ivantrykosh.app.spofidata.domain.use_case.user

import com.ivantrykosh.app.spofidata.common.Resource
import com.ivantrykosh.app.spofidata.domain.model.Track
import com.ivantrykosh.app.spofidata.domain.model.toTrack
import com.ivantrykosh.app.spofidata.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetUserTopTracksUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(token: String, timeRange: String, limit: Int): Flow<Resource<List<Track>>> = flow {
        try {
            emit(Resource.Loading())
            val topTracks = repository.getUserTopTracks("Bearer $token", timeRange, limit).items.map { it.toTrack() }
            emit(Resource.Success(topTracks))
        } catch (e: HttpException) {
            emit(Resource.Error(e.code()))
        } catch (e: Exception) {
            emit(Resource.Error(-1))
        }
    }
}