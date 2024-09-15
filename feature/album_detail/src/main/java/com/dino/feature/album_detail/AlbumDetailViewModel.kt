package com.dino.feature.album_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dino.core.domain.usecase.GetAlbumUseCase
import com.dino.core.ui.base.BaseViewModel
import com.dino.feature.album_detail.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAlbumUseCase: GetAlbumUseCase,
) : BaseViewModel() {

    private val albumIdStream: StateFlow<String?> = savedStateHandle.getStateFlow("albumId", null)

    val uiStateStream: StateFlow<AlbumDetailUiState> = albumIdStream.map { id ->
        if (id.isNullOrBlank()) {
            AlbumDetailUiState.Error
        } else {
            val albumDetail = getAlbumUseCase(id)?.toUiModel()
            if (albumDetail == null) {
                AlbumDetailUiState.NotFound
            } else {
                AlbumDetailUiState.Found(albumDetail)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AlbumDetailUiState.Loading
    )
}
