package com.ilham.event.movie_detail.domain.models

data class MovieDetail(
    val backdropPath: String = "",
    val genreIds: List<String> = emptyList(),
    val id: Int = 0,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String? = null,
    val title: String = "",
    val voteAverage: Double? = null,
    val voteCount: Int = 0,
    val video: Boolean = false,
    val cast: List<Cast> = emptyList(),
    val language: List<String> = emptyList(),
    val productionCountry: List<String> = emptyList(),
    val reviews: List<Review> = emptyList(),
    val runTime: String = ""
)

data class Cast(
    val id: Int,
    val name: String,
    val genderRole: String,
    val character: String,
    val profilePath: String?,
) {
    private val nameParts = name.split(" ", limit = 2)
    val firstName = nameParts[0]
    val lastName = nameParts[1]
}

data class Review(
    val author: String,
    val content: String,
    val id: String,
    val createdAt: String,
    val rating:Double
)