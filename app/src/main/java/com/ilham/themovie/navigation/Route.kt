package com.ilham.themovie.navigation

import com.ilham.event.util.Constans

sealed class Route {
    data class HomeScreen(val route: String = "homeScreen") : Route()
    data class FilmScreen(
        val route: String = "FilmScreen",
        val routeWithArgs: String = "$route/{${Constans.MOVIE_ID}}",
    ) : Route() {
        fun getRouteWithArgs(id: Int): String {
            return "$route/$id"
        }
    }
}