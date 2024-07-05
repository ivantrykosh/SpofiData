package com.ivantrykosh.app.spofidata.common

import androidx.annotation.StringRes
import com.ivantrykosh.app.spofidata.R

object Constants {
    const val BASE_SPOTIFY_API_URL = "https://api.spotify.com/v1/"

    val terms = listOf("short_term", "medium_term", "long_term")
    val adaptiveTerms = mapOf<String, @receiver:StringRes Int>(terms[0] to R.string.one_month, terms[1] to R.string.six_month, terms[2] to R.string.one_year)
}