package com.ilham.event.util

object GenreConstants {

    private val genreMap = mapOf(
        28 to "Action",
        12 to "Adventure",
        16 to "Animation",
        35 to "Comedy",
        80 to "Crime",
        99 to "Documentary",
        18 to "Drama",
        10751 to "Family",
        14 to "Fantasy",
        36 to "History",
        10402 to "Music",
        9684 to "Mystery",
        10745 to "Romance",
        878 to "Sience Fiction",
        10770 to "TV Moview",
        10757 to "War",
        37 to "WEster",

    )

    fun getGenreNameById(id: Int): String {
        return genreMap[id] ?: "Unknown"
    }

}