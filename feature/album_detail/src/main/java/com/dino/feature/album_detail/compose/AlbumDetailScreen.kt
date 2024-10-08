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
        onResumeClick = viewModel::resume,
        onSkipToPreviousSongClick = viewModel::skipToPreviousSong,
        onSkipToNextSongClick = viewModel::skipToNextSong,
        onVolumeChange = viewModel::setVolume,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailScreen(
    uiState: AlbumDetailUiState,
    onPlayClick: () -> Unit,
    onShuffleClick: () -> Unit,
    onPlaySongClick: (SongModel) -> Unit,
    onPauseClick: () -> Unit,
    onResumeClick: () -> Unit,
    onSkipToPreviousSongClick: () -> Unit,
    onSkipToNextSongClick: () -> Unit,
    onVolumeChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
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
                    currentSong = uiState.currentSong,
                    isPlaying = uiState.isPlaying,
                    isShowingPlayer = uiState.isShowingPlayer,
                    progress = uiState.progress,
                    volume = uiState.volume,
                    onPlayClick = onPlayClick,
                    onShuffleClick = onShuffleClick,
                    onPlaySongClick = onPlaySongClick,
                    onPauseClick = onPauseClick,
                    onResumeClick = onResumeClick,
                    onSkipToPreviousSongClick = onSkipToPreviousSongClick,
                    onSkipToNextSongClick = onSkipToNextSongClick,
                    onVolumeChange = onVolumeChange,
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
        onResumeClick = {},
        onSkipToPreviousSongClick = {},
        onSkipToNextSongClick = {},
        onVolumeChange = {},
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
        onResumeClick = {},
        onSkipToPreviousSongClick = {},
        onSkipToNextSongClick = {},
        onVolumeChange = {},
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
        onResumeClick = {},
        onSkipToPreviousSongClick = {},
        onSkipToNextSongClick = {},
        onVolumeChange = {},
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
            currentSong = null,
            isPlaying = false,
            isShowingPlayer = false,
            progress = 0.9f,
            volume = 0.5f,
        ),
        onPlayClick = {},
        onShuffleClick = {},
        onPlaySongClick = {},
        onPauseClick = {},
        onResumeClick = {},
        onSkipToPreviousSongClick = {},
        onSkipToNextSongClick = {},
        onVolumeChange = {},
    )
}
