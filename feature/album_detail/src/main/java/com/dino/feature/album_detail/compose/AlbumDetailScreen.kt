package com.dino.feature.album_detail.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dino.core.ui.compose.Error
import com.dino.core.ui.compose.Loading
import com.dino.core.ui.compose.NavigationIcon
import com.dino.feature.album_detail.AlbumDetailUiState
import com.dino.feature.album_detail.AlbumDetailViewModel
import com.dino.feature.album_detail.model.AlbumDetailModel
import com.dino.feature.album_detail.model.SongModel
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AlbumDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumDetailViewModel = viewModel(),
) {
    val uiState by viewModel.uiStateStream.collectAsStateWithLifecycle()
    AlbumDetailScreen(
        modifier = modifier,
        uiState = uiState,
        onPlayClick = viewModel::play,
        onShuffleClick = viewModel::shuffle,
        onPlaySongClick = viewModel::playSong,
        onPauseClick = viewModel::pause,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailScreen(
    uiState: AlbumDetailUiState,
    onPlayClick: () -> Unit,
    onShuffleClick: () -> Unit,
    onPlaySongClick: (SongModel) -> Unit,
    modifier: Modifier = Modifier,
    onPauseClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = { NavigationIcon() }
            )
        }
    ) { paddingValues ->
        when (uiState) {
            AlbumDetailUiState.Error -> {
                Error(modifier = Modifier.padding(paddingValues))
            }

            AlbumDetailUiState.Loading -> {
                Loading(modifier = Modifier.padding(paddingValues))
            }

            AlbumDetailUiState.NotFound -> {
                AlbumDetailNotFoundScreen(modifier = Modifier.padding(paddingValues))
            }

            is AlbumDetailUiState.Found -> {
                AlbumDetailFoundScreen(
                    modifier = Modifier.padding(paddingValues),
                    album = uiState.album,
                    isPlaying = uiState.isPlaying,
                    onPlayClick = onPlayClick,
                    onShuffleClick = onShuffleClick,
                    onPlaySongClick = onPlaySongClick,
                    onPauseClick = onPauseClick,
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AlbumDetailScreenPreviewError() {
    AlbumDetailScreen(
        uiState = AlbumDetailUiState.Error,
        onPlayClick = {},
        onShuffleClick = {},
        onPlaySongClick = {},
        onPauseClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun AlbumDetailScreenPreviewLoading() {
    AlbumDetailScreen(
        uiState = AlbumDetailUiState.Loading,
        onPlayClick = {},
        onShuffleClick = {},
        onPlaySongClick = {},
        onPauseClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun AlbumDetailScreenPreviewNotFound() {
    AlbumDetailScreen(
        uiState = AlbumDetailUiState.NotFound,
        onPlayClick = {},
        onShuffleClick = {},
        onPlaySongClick = {},
        onPauseClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun AlbumDetailScreenPreviewFound() {
    AlbumDetailScreen(
        uiState = AlbumDetailUiState.Found(
            album = AlbumDetailModel(
                title = "Album 1",
                artist = "Artist 1",
                fileName = null,
                songs = List(10) {
                    SongModel(
                        id = "song:$it",
                        title = "Song $it",
                        fileName = null,
                    )
                }.toImmutableList()
            ),
            isPlaying = false
        ),
        onPlayClick = {},
        onShuffleClick = {},
        onPlaySongClick = {},
        onPauseClick = {},
    )
}
