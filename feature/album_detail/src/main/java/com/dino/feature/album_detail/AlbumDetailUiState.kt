package com.dino.feature.album_detail

import com.dino.feature.album_detail.model.AlbumDetailModel

sealed interface AlbumDetailUiState {
    data object Loading : AlbumDetailUiState
    data object Error : AlbumDetailUiState
    data object NotFound : AlbumDetailUiState
    data class Found(
        val album: AlbumDetailModel,
        val isPlaying: Boolean,
    ) : AlbumDetailUiState
}
