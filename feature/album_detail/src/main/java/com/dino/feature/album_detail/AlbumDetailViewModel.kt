package com.dino.feature.album_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dino.common.musicplayer.model.PlayerState
import com.dino.core.domain.usecase.album.GetAlbumUseCase
import com.dino.core.domain.usecase.player.AddSongsUseCase
import com.dino.core.domain.usecase.player.GetMediaEventStreamUseCase
import com.dino.core.domain.usecase.player.PauseSongUseCase
import com.dino.core.domain.usecase.player.PlaySongUseCase
import com.dino.core.ui.base.BaseViewModel
import com.dino.feature.album_detail.model.AlbumDetailModel
import com.dino.feature.album_detail.model.SongModel
import com.dino.feature.album_detail.model.toSong
import com.dino.feature.album_detail.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAlbumUseCase: GetAlbumUseCase,
    private val addSongsUseCase: AddSongsUseCase,
    private val playSongUseCase: PlaySongUseCase,
    private val pauseSongUseCase: PauseSongUseCase,
    getMediaEventStreamUseCase: GetMediaEventStreamUseCase,
) : BaseViewModel() {

    private val albumIdStream: StateFlow<String?> = savedStateHandle.getStateFlow("albumId", null)
    private val albumStream: StateFlow<AlbumDetailModel?> = albumIdStream.map {
        if (it.isNullOrBlank()) {
            null
        } else {
            getAlbumUseCase(it)?.toUiModel()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val uiStateStream: StateFlow<AlbumDetailUiState> = combine(
        albumIdStream,
        albumStream,
        getMediaEventStreamUseCase(),
    ) {
            albumId,
            album,
            mediaEvent,
        ->
        when {
            albumId.isNullOrBlank() -> AlbumDetailUiState.Error
            album == null -> AlbumDetailUiState.NotFound
            else -> AlbumDetailUiState.Found(album, mediaEvent.playerState == PlayerState.PLAYING)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AlbumDetailUiState.Loading
    )

    fun play() {
        val album = albumStream.value ?: return
        addAlbum(album)

        playSongUseCase(0)
    }

    fun shuffle() {
        val album = albumStream.value ?: return
        addAlbum(album)

        playSongUseCase(Random.nextInt(album.songs.size))
    }

    fun playSong(song: SongModel) {
        val album = albumStream.value ?: return
        addAlbum(album)

        val songIndex = album.songs.indexOf(song)
        playSongUseCase(songIndex)
    }

    private fun addAlbum(album: AlbumDetailModel) {
        addSongsUseCase(
            album.songs
                .map {
                    it.toSong(
                        albumTitle = album.title,
                        artist = album.artist,
                    )
                }
        )
    }

    fun pause() {
        pauseSongUseCase()
    }

}
