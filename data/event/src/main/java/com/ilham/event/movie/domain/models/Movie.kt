package com.ilham.event.movie.domain.models

import com.google.gson.annotations.SerializedName

data class Movie(

    val overview: String,

    val originalLanguage: String,

    val originalTitle: String,

    val video: Boolean,

    val title: String,

    val genreIds: List<String>,

    val posterPath: String,

    val backdropPath: String,

    val releaseDate: String,

    val popularity: Double,

    val voteAverage: Double,

    val id: Int,

    val adult: Boolean,

    val voteCount: Int

)
