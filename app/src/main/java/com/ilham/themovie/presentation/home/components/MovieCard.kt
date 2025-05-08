package com.ilham.themovie.presentation.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilham.themovie.ui.theme.TheMovieTheme

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    shapes: CornerBasedShape = MaterialTheme.shapes.large,
    bgColor: Color = Color.Black.copy(.8f),
    content: @Composable () -> Unit = {}
) {

    Card(
        modifier = modifier,
        shape = shapes,
        colors = CardDefaults.cardColors(
            containerColor = bgColor,
            contentColor = Color.White
        ),

    ) {
        content()
    }

}

@Preview
@Composable
fun MovieCardPreview() {
    TheMovieTheme {
        Surface {
            MovieCard()
        }
    }
}