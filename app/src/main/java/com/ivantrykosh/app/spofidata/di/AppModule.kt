package com.ivantrykosh.app.spofidata.di

import com.ivantrykosh.app.spofidata.common.Constants
import com.ivantrykosh.app.spofidata.data.remote.ArtistApi
import com.ivantrykosh.app.spofidata.data.remote.UserApi
import com.ivantrykosh.app.spofidata.data.repository.ArtistRepositoryImpl
import com.ivantrykosh.app.spofidata.data.repository.UserRepositoryImpl
import com.ivantrykosh.app.spofidata.domain.repository.ArtistRepository
import com.ivantrykosh.app.spofidata.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val client = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideArtistApi(): ArtistApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_SPOTIFY_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArtistApi::class.java)
    }

    @Provides
    @Singleton
    fun provideArtistRepository(artistApi: ArtistApi): ArtistRepository {
        return ArtistRepositoryImpl(artistApi)
    }

    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_SPOTIFY_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userApi: UserApi): UserRepository {
        return UserRepositoryImpl(userApi)
    }
}