package com.ilham.themovie.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.ilham.event.util.Constans
import com.ilham.themovie.presentation.detail.MovieDetailScreen
import com.ilham.themovie.presentation.home.HomeScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen().route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(
            route = Route.HomeScreen().route,
            enterTransition = { fadeIn() + scaleIn() },
            exitTransition = { fadeOut() + shrinkOut() }
        ) {
            HomeScreen(
                onMovieClick = {
                    navController.navigate(
                        Route.FilmScreen().getRouteWithArgs(id = it)
                    ) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) { inclusive = false }
                    }
                }
            )
        }

        composable(
            route = Route.FilmScreen().routeWithArgs,
            arguments = listOf(navArgument(name = Constans.MOVIE_ID) { type = NavType.IntType })
        ) {
            MovieDetailScreen(
                onNavigateUp = {
                    navController.navigateUp()
                },
                onMovieClick = {
                    navController.navigate(
                        Route.FilmScreen().getRouteWithArgs(id = it)
                    ) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) { inclusive = false }
                    }
                },
                onActorClick = {}
            )
        }
    }

}