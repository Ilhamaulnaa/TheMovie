package com.ilham.themovie.presentation.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ilham.themovie.presentation.home.components.BodyContent
import com.ilham.themovie.presentation.home.components.TopContent
import com.ilham.themovie.ui.component.LoadingView
import com.ilham.themovie.ui.theme.TheMovieTheme
import kotlinx.coroutines.delay

val defaultPadding = 16.dp
val itemPadding = 8.dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onMovieClick: (id: Int) -> Unit = {}
) {

    var isAutoScrolling by remember {
        mutableStateOf(true)
    }

    val state by homeViewModel.homeState.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { state.discoverMovie.size }
    )

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(key1 = pagerState.currentPage){
        if (isDragged){
            isAutoScrolling = false
        }else{
            isAutoScrolling = true
            delay(5000)
            with(pagerState){
                val target = if (currentPage < state.discoverMovie.size - 1) currentPage + 1 else 0
                scrollToPage(target)
            }
        }
    }
    
    Box(modifier = modifier){
        AnimatedVisibility(visible = state.error != null) {
            Text(
                text = state.error ?: "Unknown Error",
                color = MaterialTheme.colorScheme.error,
                maxLines = 2
            )
        }
        AnimatedVisibility(visible = !state.isLoading && state.error == null) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val boxHeight = maxHeight
                val topItemHeight = boxHeight * .45f
                val bodyItemHeight = boxHeight * .55f
                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(defaultPadding),
                    pageSize = PageSize.Fill,
                    pageSpacing = itemPadding
                ) { page -> 
                    if (isAutoScrolling){
                        AnimatedContent(
                            targetState = page,
                            label = ""
                        ) { index ->
                            TopContent(
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .heightIn(min = topItemHeight),
                                movie = state.discoverMovie[index],
                                onMovieClick = {
                                    onMovieClick(it)
                                }
                            )
                        }
                    } else {
                        TopContent(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .heightIn(min = topItemHeight),
                            movie = state.discoverMovie[page],
                            onMovieClick = {
                                onMovieClick(it)
                            }
                        )
                    }
                }
                BodyContent(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .heightIn(max = bodyItemHeight),
                    discoverMovie = state.discoverMovie,
                    popularMovie = state.popularMovie,
                    onMovieClick = onMovieClick
                )
            }
        }
    }
    LoadingView(isLoading = state.isLoading)

}

@Preview
@Composable
fun HomeScreenPreview() {
    TheMovieTheme {
        Surface {
            HomeScreen(
                onMovieClick = {}
            )
        }
    }
}