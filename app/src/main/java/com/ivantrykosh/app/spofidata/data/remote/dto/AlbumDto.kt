package com.ivantrykosh.app.spofidata.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AlbumDto(
    val id: String,
    val uri: String,
    val name: String,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrl,
    val images: List<Image>,
    val artists: List<ArtistDto>,
)
