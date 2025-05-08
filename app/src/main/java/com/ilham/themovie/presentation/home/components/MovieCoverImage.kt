package com.ilham.themovie.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ilham.event.movie.domain.models.Movie
import com.ilham.event.util.Constans
import com.ilham.themovie.presentation.home.itemPadding
import com.ilham.themovie.ui.theme.TheMovieTheme

@Composable
fun MovieCoverImage(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClick: (Int) -> Unit
) {

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data("${Constans.BASE_IMAGE_URL}${movie.posterPath}")
        .crossfade(true)
        .build()

    Box(
        modifier = modifier
            .size(width = 150.dp, height = 250.dp)
            .padding(itemPadding)
            .clickable {
                onMovieClick(movie.id)
            }
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = "Cover Image",
            modifier = Modifier
                .matchParentSize()
                .clip(MaterialTheme.shapes.medium)
                .shadow(elevation = 4.dp),
            contentScale = ContentScale.Crop
        )
        MovieCard(
            shapes = CircleShape,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Bookmark,
                contentDescription = null,
                modifier = Modifier.padding(4.dp)
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            color = Color.Black.copy(.8f),
            contentColor = Color.White,
            shape = RoundedCornerShape(
                bottomStart = 30.dp,
                bottomEnd = 30.dp
            )
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = movie.title,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieCoverImagePreview() {
    TheMovieTheme {
        Surface {
            val movie = Movie(
                overview = "" ,
                originalLanguage = "Indo",
                originalTitle = "Natdman",
                video = true,
                title = "Batman",
                genreIds = listOf("Action", "Family"),
                posterPath = "",
                backdropPath = "",
                releaseDate = "",
                popularity = 209.0,
                voteAverage = 8.9,
                id = 1,
                adult = true,
                voteCount = 2
            )
            MovieCoverImage(
                movie = movie ,
                onMovieClick = {}
            )
        }
    }
}