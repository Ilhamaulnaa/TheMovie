package com.ilham.themovie.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ilham.event.movie.domain.models.Movie
import com.ilham.themovie.presentation.home.itemPadding
import com.ilham.themovie.ui.theme.TheMovieTheme

@Composable
fun BodyContent(
    modifier: Modifier = Modifier,
    discoverMovie: List<Movie> = emptyList(),
    popularMovie: List<Movie> = emptyList(),
    onMovieClick: (id:Int) -> Unit = {}
) {
    
    LazyColumn(modifier = modifier){
        item {
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
                containerColor = Color.Black,
            )) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = itemPadding),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Discover Movies",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowForwardIos ,
                            contentDescription = "More Discover Movie",
                            tint = Color.White
                        )
                    }
                }
                LazyRow{
                    items(discoverMovie){
                        MovieCoverImage(
                            movie = it,
                            onMovieClick = {
                                onMovieClick(it)
                            }
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = itemPadding),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Popular Movies",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowForwardIos ,
                            contentDescription = "More Popular Movies",
                            tint = Color.White
                        )
                    }
                }
                LazyRow{
                    items(popularMovie){
                        MovieCoverImage(
                            movie = it,
                            onMovieClick = {
                                onMovieClick(it)
                            }
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun BodyContentPreview() {
    TheMovieTheme {
        Surface {
            BodyContent()
        }
    }
}