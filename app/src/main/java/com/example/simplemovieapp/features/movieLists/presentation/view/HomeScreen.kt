package com.example.simplemovieapp.features.movieLists.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.material.MovieTHDBTheme
import com.example.material.TMDBTheme
import com.example.simplemovieapp.R
import com.example.simplemovieapp.features.movieLists.domain.models.MovieDomain
import com.example.simplemovieapp.features.movieLists.presentation.viewModel.HomeUiState
import com.example.simplemovieapp.features.movieLists.presentation.viewModel.HomeViewModel

/**
 * MovieListScreen
 *
 * @author (c) 2024, Hugo Figueroa
 * */

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState by viewModel.movieList.observeAsState()
    when (uiState) {
        is HomeUiState.Loading, null -> {
            CircularProgressIndicator()
        }

        is HomeUiState.Content -> {
            MovieListWithToggle(listMovies = (uiState as HomeUiState.Content).data)
        }

        is HomeUiState.Error -> {

        }
    }
}

@Composable
fun MovieListWithToggle(listMovies: List<MovieDomain>) {
    var isGrid by remember { mutableStateOf(false) }
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

        if (isGrid) {
            GridList(items = listMovies)
        } else {
            VerticalList(items = listMovies)
        }
    }
}

@Composable
fun VerticalList(items: List<MovieDomain>) {
    LazyColumn {
        items(items) { item ->
            MovieListItem("https://image.tmdb.org/t/p/w500", item)
        }
    }
}

@Composable
fun GridList(items: List<MovieDomain>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(items) { item ->
            MovieGridItem("https://image.tmdb.org/t/p/w500", item)
        }
    }
}

@Composable
fun MovieListItem(imageBaseUrl: String, item: MovieDomain) {
    Box(
        modifier = Modifier
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
                    Text(text = item.overview, maxLines = 2)
                }
            }

            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {
                val posterUrl = imageBaseUrl.plus(item.posterPath)
                AsyncImage(
                    model = posterUrl,
                    placeholder = painterResource(R.drawable.poster),
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
fun MovieGridItem(imageBaseUrl: String, item: MovieDomain) {
    Card(
        modifier = Modifier
            .width(180.dp)
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
                placeholder = painterResource(R.drawable.poster),
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
            Text(
                modifier = Modifier
                    .padding(TMDBTheme.grids.grid1),
                text = item.voteAverage.toString(), maxLines = 2
            )
        }
    }
}

@Preview
@Composable
fun previewMovieListItem() {
    MovieTHDBTheme {
        MovieListItem(
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
            )
        )
    }
}

@Preview
@Composable
fun previewMovieGridItem() {
    MovieTHDBTheme {
        MovieGridItem(
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
            )
        )
    }
}

@Preview
@Composable
fun previewMovieList() {
    val listMovies = listOf(
        MovieDomain(
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
        ), MovieDomain(
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
        ), MovieDomain(
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
        )
    )
    MovieTHDBTheme {
        MovieListWithToggle(listMovies = listMovies)
    }
}