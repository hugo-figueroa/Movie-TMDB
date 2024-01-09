package com.example.simplemovieapp.features.movieDetails.presentation.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.example.simplemovieapp.features.movieDetails.domain.models.GenreDomain
import com.example.simplemovieapp.features.movieDetails.domain.models.LanguagesDomain
import com.example.simplemovieapp.features.movieDetails.domain.models.MovieDetailsDomain
import com.example.simplemovieapp.features.movieDetails.presentation.viewModel.MovieDetailsUiState
import com.example.simplemovieapp.features.movieDetails.presentation.viewModel.MovieDetailsViewModel

/**
 * MovieDetailsScreen
 *
 * @author (c) 2024, Hugo Figueroa
 * */
@Composable
fun MovieDetailScreen(viewModel: MovieDetailsViewModel) {
    val uiState by viewModel.movieDetails.observeAsState()
    when (uiState) {
        is MovieDetailsUiState.Loading, null -> {
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

        is MovieDetailsUiState.Content -> {
            MovieDetails(
                viewModel,
                (uiState as MovieDetailsUiState.Content).data
            )
        }

        is MovieDetailsUiState.Error -> {
            when ((uiState as MovieDetailsUiState.Error).error) {
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
fun MovieDetails(viewModel: MovieDetailsViewModel, item: MovieDetailsDomain) {
    var isFavorite by remember { mutableStateOf(viewModel.isInFavorites) }

    Column(
        modifier = Modifier
            .background(TMDBTheme.colors.surfaceLight)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = TMDBTheme.grids.grid2)
                .fillMaxWidth(),
            text = "Details",
            textAlign = TextAlign.Center,
            style = TMDBTheme.typography.title1,
            color = TMDBTheme.colors.primaryVariant,
            maxLines = 2
        )

        val posterUrl = viewModel.baseImageUrl.plus("w500").plus(item.posterPath)
        AsyncImage(
            model = posterUrl,
            placeholder = painterResource(R.drawable.poster_placeholder),
            contentDescription = item.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(180.dp)
                .padding(top = TMDBTheme.grids.grid3)
                .clip(TMDBTheme.customShapes.roundedCorner8)
        )

        Row(
            modifier = Modifier
                .padding(
                    end = TMDBTheme.grids.grid3
                )
                .fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            IconToggleButton(
                modifier = Modifier,
                checked = isFavorite ?: false,
                onCheckedChange = {
                    isFavorite = it
                    onFavoriteChange(it, viewModel)
                }) {
                if (isFavorite == true) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.favorite_fill),
                        contentDescription = "",
                        tint = TMDBTheme.colors.primaryVariant
                    )
                } else {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.favorite),
                        contentDescription = "",
                        tint = TMDBTheme.colors.primaryVariant
                    )
                }
            }
        }

        Text(
            modifier = Modifier
                .padding(
                    start = TMDBTheme.grids.grid3,
                    end = TMDBTheme.grids.grid3,
                    top = TMDBTheme.grids.grid2
                )
                .fillMaxWidth(),
            text = item.title,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            minLines = 2
        )

        Text(
            modifier = Modifier
                .padding(
                    start = TMDBTheme.grids.grid3,
                    end = TMDBTheme.grids.grid3,
                    bottom = TMDBTheme.grids.grid1
                )
                .fillMaxWidth(),
            text = item.overview
        )

        StatusView(item)
        ReleaseView(item)
        VoteAverageView(item)
        GenresList(item.genres)
        LanguagesList(item.spokenLanguages)
        PopularityView(item)
    }
}

private fun onFavoriteChange(isFavorite: Boolean, viewModel: MovieDetailsViewModel) {
    if (isFavorite) {
        viewModel.saveMovieToFavorites(viewModel.movie)
    } else {
        viewModel.removeMovieToFavorites(viewModel.movie.id)
    }
}

@Composable
fun ReleaseView(item: MovieDetailsDomain) {
    Row(
        modifier = Modifier
            .padding(
                start = TMDBTheme.grids.grid3,
                end = TMDBTheme.grids.grid3,
                top = TMDBTheme.grids.grid1,
                bottom = TMDBTheme.grids.grid1
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Release Date:",
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier
                .padding(
                    start = TMDBTheme.grids.grid2,
                ),
            text = item.releaseDate
        )
    }
}

@Composable
fun VoteAverageView(item: MovieDetailsDomain) {
    Row(
        modifier = Modifier
            .padding(
                start = TMDBTheme.grids.grid3,
                end = TMDBTheme.grids.grid3,
                bottom = TMDBTheme.grids.grid1
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Vote Average:",
            fontWeight = FontWeight.Bold,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenresList(genres: List<GenreDomain>) {
    Text(
        modifier = Modifier
            .padding(
                start = TMDBTheme.grids.grid3,
                end = TMDBTheme.grids.grid3,
                bottom = TMDBTheme.grids.grid1
            )
            .fillMaxWidth(),
        text = "Genres:",
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold
    )

    FlowRow(
        modifier = Modifier
            .padding(
                start = TMDBTheme.grids.grid3,
                end = TMDBTheme.grids.grid3,
                bottom = TMDBTheme.grids.grid1
            )
            .heightIn(max = 200.dp)
            .fillMaxWidth()
    ) {
        genres.forEach {
            GenreItem(it)
        }
    }
}

@Composable
fun GenreItem(item: GenreDomain) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(TMDBTheme.grids.grid1),
        elevation = TMDBTheme.grids.grid05,
        shape = TMDBTheme.customShapes.roundedCorner16,
        backgroundColor = TMDBTheme.colors.grayLight
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(TMDBTheme.grids.grid1),
                text = item.name, maxLines = 2,
                fontWeight = FontWeight.Bold,
                color = TMDBTheme.colors.secondary
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LanguagesList(languages: List<LanguagesDomain>) {
    Text(
        modifier = Modifier
            .padding(
                start = TMDBTheme.grids.grid3,
                end = TMDBTheme.grids.grid3,
                bottom = TMDBTheme.grids.grid1
            )
            .fillMaxWidth(),
        text = "Languages:",
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold
    )

    FlowRow(
        modifier = Modifier
            .padding(
                start = TMDBTheme.grids.grid3,
                end = TMDBTheme.grids.grid3,
                bottom = TMDBTheme.grids.grid1
            )
            .heightIn(max = 200.dp)
            .fillMaxWidth()
    ) {
        languages.forEach {
            LanguageItem(it)
        }
    }
}

@Composable
fun LanguageItem(item: LanguagesDomain) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(TMDBTheme.grids.grid1),
        elevation = TMDBTheme.grids.grid05,
        shape = TMDBTheme.customShapes.roundedCorner16,
        backgroundColor = TMDBTheme.colors.grayLight
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(TMDBTheme.grids.grid1),
                text = item.name, maxLines = 2,
                fontWeight = FontWeight.Bold,
                color = TMDBTheme.colors.secondary
            )
        }
    }
}

@Composable
fun PopularityView(item: MovieDetailsDomain) {
    Row(
        modifier = Modifier
            .padding(
                start = TMDBTheme.grids.grid3,
                end = TMDBTheme.grids.grid3,
                top = TMDBTheme.grids.grid1,
                bottom = TMDBTheme.grids.grid1
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Popularity:",
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier
                .padding(
                    start = TMDBTheme.grids.grid2,
                ),
            text = item.popularity.toString()
        )
    }
}

@Composable
fun StatusView(item: MovieDetailsDomain) {
    Row(
        modifier = Modifier
            .padding(
                start = TMDBTheme.grids.grid3,
                end = TMDBTheme.grids.grid3,
                top = TMDBTheme.grids.grid1,
                bottom = TMDBTheme.grids.grid1
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Status:",
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier
                .padding(
                    start = TMDBTheme.grids.grid2,
                ),
            text = item.status
        )
    }
}
