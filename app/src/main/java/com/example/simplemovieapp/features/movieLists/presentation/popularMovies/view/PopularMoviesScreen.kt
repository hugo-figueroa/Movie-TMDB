package com.example.simplemovieapp.features.movieLists.presentation.popularMovies.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.material.MovieTHDBTheme
import com.example.material.TMDBTheme
import com.example.networking.exceptions.NoInternetException
import com.example.networking.exceptions.NotFoundException
import com.example.networking.exceptions.ServerErrorException
import com.example.networking.exceptions.UnknownErrorException
import com.example.simplemovieapp.R
import com.example.simplemovieapp.features.movieLists.domain.models.MovieDomain
import com.example.simplemovieapp.features.movieLists.presentation.popularMovies.viewModel.PopularMoviesUiState
import com.example.simplemovieapp.features.movieLists.presentation.popularMovies.viewModel.PopularMoviesViewModel

/**
 * PopularMoviesScreen
 *
 * @author (c) 2024, Hugo Figueroa
 * */

@Composable
fun PopularMoviesScreen(viewModel: PopularMoviesViewModel) {
    val uiState by viewModel.popularMovies.observeAsState()
    when (uiState) {
        is PopularMoviesUiState.Loading, null -> {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(64.dp),
                    color = TMDBTheme.colors.primary,
                    backgroundColor = TMDBTheme.colors.orangeLight,
                    strokeWidth = 6.dp,
                    strokeCap = StrokeCap.Round
                )
            }

        }

        is PopularMoviesUiState.Content -> {
            PopularMoviesWithToggle(
                viewModel = viewModel
            )
        }

        is PopularMoviesUiState.Error -> {
            when ((uiState as PopularMoviesUiState.Error).error) {
                is NoInternetException -> {
                    // Internet Error
                    //TODO: Add design for error
                }

                is NotFoundException -> {
                    // Not Found Information
                    //TODO: Add design for error
                }

                is ServerErrorException -> {
                    // Server Error
                    //TODO: Add design for error
                }

                is UnknownErrorException -> {
                    // Unknown Error
                    //TODO: Add design for error
                }

                else -> {
                    // Unknown Error
                    //TODO: Add design for error
                }
            }
        }
    }
}

@Composable
fun PopularMoviesWithToggle(viewModel: PopularMoviesViewModel) {
    var isGrid by remember { mutableStateOf(false) }

    val scrollListState = rememberLazyListState()
    val scrollGridState = rememberLazyGridState()

    Column(
        modifier = Modifier
            .background(TMDBTheme.colors.surfaceLight)
            .padding(top = TMDBTheme.grids.grid2)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Popular Movies",
            textAlign = TextAlign.Center,
            style = TMDBTheme.typography.title1,
            color = TMDBTheme.colors.primary,
            maxLines = 2
        )

        IconToggleButton(checked = isGrid, onCheckedChange = { isGrid = it }) {
            if (isGrid) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.list),
                    contentDescription = "List View"
                )
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.grid),
                    contentDescription = "Grid View"
                )
            }
        }

        val listMoviesList = remember { mutableStateOf(viewModel.popularMoviesList) }
        val listMoviesGrid = remember { mutableStateOf(viewModel.popularMoviesGrid) }

        if (isGrid) {
            PopularMoviesGridList(
                viewModel = viewModel,
                listMovies = listMoviesGrid,
                scrollGridState = scrollGridState
            )
        } else {
            PopularMoviesVerticalList(
                viewModel = viewModel,
                listMovies = listMoviesList,
                scrollListState = scrollListState
            )
        }
    }
}

@Composable
fun PopularMoviesVerticalList(
    viewModel: PopularMoviesViewModel,
    listMovies: MutableState<MutableList<MovieDomain>>,
    scrollListState: LazyListState
) {
    var isLoading by remember { mutableStateOf(false) }
    isLoading = false

    LazyColumn(
        state = scrollListState
    ) {
        items(listMovies.value) { movie ->
            PopularMoviesItem(
                viewModel.baseImageUrl.plus("w500"),
                movie
            ) { movieId -> viewModel.goToMovieDetails(movieId) }
            if (movie == listMovies.value.last()) {
                if (!isLoading) {
                    LaunchedEffect(key1 = Unit) {
                        isLoading = true
                        viewModel.popularMoviesPageList += 1
                        viewModel.fetchPopularMoviesList(viewModel.popularMoviesPageList)
                    }
                }
            }
        }
        item {
            if (isLoading) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(32.dp),
                        color = TMDBTheme.colors.primary,
                        backgroundColor = TMDBTheme.colors.orangeLight,
                        strokeCap = StrokeCap.Round
                    )
                }
            }
        }
    }
}

@Composable
fun PopularMoviesGridList(
    viewModel: PopularMoviesViewModel,
    listMovies: MutableState<MutableList<MovieDomain>>,
    scrollGridState: LazyGridState
) {
    var isLoading by remember { mutableStateOf(false) }
    isLoading = false

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = scrollGridState
    ) {
        items(listMovies.value) { movie ->
            PopularMoviesGridItem(
                viewModel.baseImageUrl.plus("w500"),
                movie
            ) { movieId -> viewModel.goToMovieDetails(movieId) }
            if (movie == listMovies.value.last()) {
                if (!isLoading) {
                    LaunchedEffect(key1 = Unit) {
                        isLoading = true
                        viewModel.popularMoviesPageGrid += 1
                        viewModel.fetchPopularMoviesGrid(viewModel.popularMoviesPageGrid)
                    }
                }
            }
        }
        item {
            if (isLoading) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(32.dp),
                        color = TMDBTheme.colors.primary,
                        backgroundColor = TMDBTheme.colors.orangeLight,
                        strokeCap = StrokeCap.Round
                    )
                }
            }
        }
    }
}

@Composable
fun PopularMoviesItem(
    imageBaseUrl: String,
    item: MovieDomain,
    onItemClick: (movieId: Int) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onItemClick(item.id) }
            .height(160.dp)
            .fillMaxWidth()
            .padding(
                start = TMDBTheme.grids.grid2,
                end = TMDBTheme.grids.grid2,
                top = TMDBTheme.grids.grid1,
                bottom = TMDBTheme.grids.grid1
            )
            .background(Color(0x00FFFFFF))
    ) {
        Box(
            Modifier.background(Color(0x00FFFFFF))
        ) {

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                elevation = TMDBTheme.grids.grid1,
                shape = TMDBTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            start = 110.dp,
                            end = TMDBTheme.grids.grid2,
                            top = TMDBTheme.grids.grid2
                        )
                ) {

                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )
                    Text(
                        text = item.overview, maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {
                val posterUrl = imageBaseUrl.plus(item.posterPath)
                AsyncImage(
                    model = posterUrl,
                    placeholder = painterResource(R.drawable.poster_placeholder),
                    contentDescription = item.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(80.dp)
                        .clip(TMDBTheme.customShapes.roundedCorner8)
                )
            }
        }
    }
}

@Composable
fun PopularMoviesGridItem(
    imageBaseUrl: String,
    item: MovieDomain,
    onItemClick: (movieId: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable { onItemClick(item.id) }
            .padding(TMDBTheme.grids.grid1),
        elevation = TMDBTheme.grids.grid05,
        shape = TMDBTheme.shapes.large
    ) {

        Column(
            modifier = Modifier
                .padding(TMDBTheme.grids.grid1),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val posterUrl = imageBaseUrl.plus(item.posterPath)
            AsyncImage(
                model = posterUrl,
                placeholder = painterResource(R.drawable.poster_placeholder),
                contentDescription = item.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(180.dp)
                    .clip(TMDBTheme.customShapes.roundedCorner8)
            )

            Text(
                modifier = Modifier
                    .padding(
                        start = TMDBTheme.grids.grid2,
                        end = TMDBTheme.grids.grid2,
                        top = TMDBTheme.grids.grid1,
                        bottom = TMDBTheme.grids.grid1
                    )
                    .fillMaxWidth(),
                text = item.title,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                minLines = 3
            )

            Card(
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp)
                    .padding(TMDBTheme.grids.grid1),
                elevation = TMDBTheme.grids.grid05,
                shape = TMDBTheme.customShapes.roundedCorner16,
                backgroundColor = TMDBTheme.colors.grayLight
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "",
                        tint = TMDBTheme.colors.yellowPrimary
                    )
                    Text(
                        modifier = Modifier
                            .padding(TMDBTheme.grids.grid1),
                        text = String.format("%.1f", item.voteAverage), maxLines = 2,
                        fontWeight = FontWeight.Bold,
                        color = TMDBTheme.colors.secondary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun previewPopularMoviesItem() {
    MovieTHDBTheme {
        PopularMoviesItem(
            imageBaseUrl = "https://image.tmdb.org/t/p/",
            item = MovieDomain(
                id = 1029575,
                language = "en",
                originalTitle = "The Family Plan",
                overview = "Dan Morgan is many things: a devoted husband, a loving father, a celebrated car salesman. He's also a former assassin. And when his past catches up to his present, he's forced to take his unsuspecting family on a road trip unlike any other.",
                popularity = 4320.505,
                posterPath = "a6syn9qcU4a54Lmi3JoIr1XvhFU.jpg",
                releaseDate = "2023-12-14",
                title = "The Family Plan",
                video = false,
                voteAverage = 7.395,
                voteCount = 625
            ),
            {}
        )
    }
}

@Preview
@Composable
fun previewPopularMoviesGridItem() {
    MovieTHDBTheme {
        PopularMoviesGridItem(
            imageBaseUrl = "https://image.tmdb.org/t/p/",
            item = MovieDomain(
                id = 1029575,
                language = "en",
                originalTitle = "The Family Plan",
                overview = "Dan Morgan is many things: a devoted husband, a loving father, a celebrated car salesman. He's also a former assassin. And when his past catches up to his present, he's forced to take his unsuspecting family on a road trip unlike any other.",
                popularity = 4320.505,
                posterPath = "a6syn9qcU4a54Lmi3JoIr1XvhFU.jpg",
                releaseDate = "2023-12-14",
                title = "The Family Plan",
                video = false,
                voteAverage = 7.395,
                voteCount = 625
            ),
            {}
        )
    }
}