package com.dino.feature.album_list

import com.dino.feature.album_list.model.AlbumModel
import kotlinx.collections.immutable.ImmutableList

sealed interface AlbumListUiState {
    data object Loading : AlbumListUiState
    data object Empty : AlbumListUiState
    data object Error : AlbumListUiState
    data class Success(val items: ImmutableList<AlbumModel>) : AlbumListUiState
}
