package com.dino.feature.album_list

import androidx.lifecycle.viewModelScope
import com.dino.core.domain.usecase.GetAlbumsUseCase
import com.dino.core.ui.base.BaseViewModel
import com.dino.feature.album_list.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
) : BaseViewModel() {

    val uiStateStream: StateFlow<AlbumListUiState> = flow {
        emit(getAlbumsUseCase().map { it.toUiModel() })
    }
        .map {
            if (it.isEmpty()) {
                AlbumListUiState.Empty
            } else {
                AlbumListUiState.Success(it.toImmutableList())
            }
        }
        .catch {
            emit(AlbumListUiState.Error)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AlbumListUiState.Loading
        )
}
