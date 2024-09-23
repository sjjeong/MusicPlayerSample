package com.dino.feature.album_list

import com.dino.common.musicplayer.model.Song
import com.dino.feature.album_list.model.AlbumModel
import kotlinx.collections.immutable.ImmutableList

sealed interface AlbumListUiState {
    data object Loading : AlbumListUiState
    data object Empty : AlbumListUiState
    data object Error : AlbumListUiState
    data class Success(
        val items: ImmutableList<AlbumModel>,
        val currentSong: Song?,
        val isPlaying: Boolean,
        val isShowingPlayer: Boolean,
        val progress: Float,
        val volume: Float,
    ) : AlbumListUiState
}
