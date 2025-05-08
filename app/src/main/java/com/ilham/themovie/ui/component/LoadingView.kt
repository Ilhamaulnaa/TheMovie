package com.ilham.themovie.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ilham.themovie.ui.theme.TheMovieTheme

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
    isLoading: Boolean
) {

    AnimatedVisibility(
        visible = isLoading,
        enter = fadeIn() + expandVertically()
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.primary.copy(
                    alpha = .3f
                )
            ) {

            }
        }
    }

}

@Preview
@Composable
fun LoadingViewPreview() {
    TheMovieTheme {
        Surface {
            LoadingView(isLoading = true)
        }
    }
}