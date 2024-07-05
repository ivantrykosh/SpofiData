package com.ivantrykosh.app.spofidata.domain.use_case.user

import com.ivantrykosh.app.spofidata.common.Resource
import com.ivantrykosh.app.spofidata.domain.model.Artist
import com.ivantrykosh.app.spofidata.domain.model.toArtist
import com.ivantrykosh.app.spofidata.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetUserTopArtistsUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(token: String, timeRange: String, limit: Int): Flow<Resource<List<Artist>>> = flow {
        try {
            emit(Resource.Loading())
            val topArtist = repository.getUserTopArtists("Bearer $token", timeRange, limit).items.map { it.toArtist() }
            emit(Resource.Success(topArtist))
        } catch (e: HttpException) {
            emit(Resource.Error(e.code()))
        } catch (e: Exception) {
            emit(Resource.Error(-1))
        }
    }
}