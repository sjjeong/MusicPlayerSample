package com.dino.feature.album_list

import androidx.lifecycle.viewModelScope
import com.dino.common.musicplayer.model.PlayerState
import com.dino.core.domain.usecase.album.GetAlbumsUseCase
import com.dino.core.domain.usecase.player.GetCurrentPositionUseCase
import com.dino.core.domain.usecase.player.GetMediaEventStreamUseCase
import com.dino.core.domain.usecase.player.PauseSongUseCase
import com.dino.core.domain.usecase.player.ResumeSongUseCase
import com.dino.core.domain.usecase.player.SetVolumeUseCase
import com.dino.core.domain.usecase.player.SkipToNextSongUseCase
import com.dino.core.domain.usecase.player.SkipToPreviousSongUseCase
import com.dino.core.ui.base.BaseViewModel
import com.dino.feature.album_list.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val pauseSongUseCase: PauseSongUseCase,
    private val resumeSongUseCase: ResumeSongUseCase,
    private val skipToPreviousSongUseCase: SkipToPreviousSongUseCase,
    private val skipToNextSongUseCase: SkipToNextSongUseCase,
    private val setVolumeUseCase: SetVolumeUseCase,
    getMediaEventStreamUseCase: GetMediaEventStreamUseCase,
    getCurrentPositionUseCase: GetCurrentPositionUseCase,
) : BaseViewModel() {

    private val mediaEventStream = getMediaEventStreamUseCase()

    @OptIn(ObsoleteCoroutinesApi::class, ExperimentalCoroutinesApi::class)
    private val currentPositionStream: StateFlow<Long> = mediaEventStream.filter { it.playerState == PlayerState.PLAYING }
        .flatMapLatest {
            ticker(100L).receiveAsFlow()
        }
        .map { getCurrentPositionUseCase() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0L
        )

    val uiStateStream: StateFlow<AlbumListUiState> = combine(
        flow {
            emit(getAlbumsUseCase().map { it.toUiModel() })
        },
        mediaEventStream,
        currentPositionStream,
    ) {
            albums,
            mediaEvent,
            currentPosition,
        ->
        if (albums.isEmpty()) {
            AlbumListUiState.Empty
        } else {
            AlbumListUiState.Success(
                items = albums.toImmutableList(),
                currentSong = mediaEvent.currentSong,
                isPlaying = mediaEvent.playerState == PlayerState.PLAYING,
                isShowingPlayer = mediaEvent.playerState != PlayerState.STOPPED,
                progress = currentPosition.toFloat() / mediaEvent.totalDuration,
                volume = mediaEvent.volume,
            )
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

    fun pause() {
        pauseSongUseCase()
    }

    fun resume() {
        resumeSongUseCase()
    }

    fun skipToPreviousSong() {
        skipToPreviousSongUseCase()
    }

    fun skipToNextSong() {
        skipToNextSongUseCase()
    }

    fun setVolume(volume: Float) {
        setVolumeUseCase(volume)
    }
}
