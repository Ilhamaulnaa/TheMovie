package com.ilham.themovie.presentation.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilham.themovie.presentation.home.defaultPadding
import com.ilham.themovie.presentation.home.itemPadding
import com.ilham.themovie.ui.theme.TheMovieTheme

@Composable
fun MovieDetail(
    modifier: Modifier = Modifier,
    title: String,
    rating: Double,
    genre: List<String>
) {

    Column(modifier = modifier.padding(defaultPadding)) {

        MovieCard {

            Row(
                modifier = Modifier.padding(4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.StarRate,
                    contentDescription = "Rating",
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = rating.toString(),
                )
            }
        }
        Spacer(modifier = Modifier.height(itemPadding))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(itemPadding))
        MovieCard {
            Row(
                modifier = Modifier.padding(4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                genre.forEachIndexed { index, genreText ->
                    if (index != 0){
                        VerticalDivider(modifier = Modifier.height(16.dp))
                    }
                    Text(
                        text = genreText,
                        modifier = Modifier
                            .padding(6.dp)
                            .weight(1f),
                        maxLines = 1
                    )
                    if (index != genre.lastIndex){
                        VerticalDivider(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MovieDetailPreview() {
    TheMovieTheme {
        Surface {
            MovieDetail(
                title = "Avengers",
                rating = 4.5,
                genre = listOf("Action","Science","Adventure", "Family")
            )
        }
    }
}