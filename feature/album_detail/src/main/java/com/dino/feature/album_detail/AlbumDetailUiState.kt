package com.dino.feature.album_detail

import com.dino.common.musicplayer.model.Song
import com.dino.feature.album_detail.model.AlbumDetailModel

sealed interface AlbumDetailUiState {
    data object Loading : AlbumDetailUiState
    data object Error : AlbumDetailUiState
    data object NotFound : AlbumDetailUiState
    data class Found(
        val album: AlbumDetailModel,
        val currentSong: Song?,
        val isPlaying: Boolean,
        val isShowingPlayer: Boolean,
        val progress: Float,
    ) : AlbumDetailUiState
}
