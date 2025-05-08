package com.ilham.themovie.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ilham.event.movie.domain.models.Movie
import com.ilham.event.util.Constans
import com.ilham.themovie.R

@Composable
fun TopContent(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClick: (id:Int) -> Unit
) {

    val imagerequest = ImageRequest.Builder(LocalContext.current)
        .data("${Constans.BASE_IMAGE_URL}${movie.posterPath}")
        .crossfade(true)
        .build()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onMovieClick(movie.id) }
    ){
        AsyncImage(
            model = imagerequest,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            onError = {
                it.result.throwable.printStackTrace()
            },
            placeholder = painterResource(id = R.drawable.img_background),
            modifier = Modifier.matchParentSize()
        )
        MovieDetail(
            title = movie.title,
            rating = movie.voteAverage,
            genre = movie.genreIds,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 20.dp)
        )
    }
    
}