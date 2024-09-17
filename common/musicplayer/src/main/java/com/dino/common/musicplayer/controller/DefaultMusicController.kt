package com.dino.common.musicplayer.controller

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.dino.common.musicplayer.PlaybackService
import com.dino.common.musicplayer.model.MediaEvent
import com.dino.common.musicplayer.model.Song
import com.dino.common.musicplayer.model.toPlayerState
import com.dino.common.musicplayer.model.toSong
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce

class DefaultMusicController(private val context: Context) : MusicController {

    private val mediaControllerFuture: ListenableFuture<MediaController>
    private val mediaController: MediaController?
        get() = if (mediaControllerFuture.isDone) mediaControllerFuture.get() else null

    override var songs: List<Song> = emptyList()
        set(value) {
            field = value
            val mediaItems = value.map {
                MediaItem.Builder()
                    .setMediaId(it.mediaId)
                    .setUri(it.toUri(context))
                    .setMediaMetadata(
                        MediaMetadata.Builder()
                            .setTitle(it.title)
                            .setArtist(it.artist)
                            .setAlbumTitle(it.albumTitle)
                            .build()
                    )
                    .build()
            }

            mediaController?.setMediaItems(mediaItems)
        }

    private val _mediaControllerStateFlow: MutableStateFlow<MediaEvent> = MutableStateFlow(MediaEvent.EMPTY)

    @OptIn(FlowPreview::class)
    override val mediaEventStream: Flow<MediaEvent> = _mediaControllerStateFlow.debounce(200L)

    override val currentPosition: Long
        get() = mediaController?.currentPosition ?: 0L

    override val currentSong: Song?
        get() = mediaController?.currentMediaItem?.toSong()

    init {
        val sessionToken = SessionToken(context, ComponentName(context, PlaybackService::class.java))
        mediaControllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        mediaControllerFuture.addListener(
            { controllerListener() },
            MoreExecutors.directExecutor()
        )
    }

    private fun controllerListener() {
        mediaController?.addListener(object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)

                with(player) {
                    _mediaControllerStateFlow.value = MediaEvent(
                        playerState = playbackState.toPlayerState(isPlaying),
                        currentMusic = currentMediaItem?.toSong(),
                        currentPosition = currentPosition.coerceAtLeast(0L),
                        totalDuration = duration.coerceAtLeast(0L),
                        isShuffleEnabled = shuffleModeEnabled,
                        isRepeatOneEnabled = repeatMode == Player.REPEAT_MODE_ONE
                    )
                }
            }
        })
    }

    override fun play(mediaItemIndex: Int) {
        mediaController?.apply {
            seekToDefaultPosition(mediaItemIndex)
            playWhenReady = true
            prepare()
        }
    }

    override fun resume() {
        mediaController?.play()
    }

    override fun pause() {
        mediaController?.pause()
    }

    override fun seekTo(position: Long) {
        mediaController?.seekTo(position)
    }

    override fun destroy() {
        MediaController.releaseFuture(mediaControllerFuture)
        _mediaControllerStateFlow.value = MediaEvent.EMPTY
    }

    override fun skipToNextSong() {
        mediaController?.seekToNext()
    }

    override fun skipToPreviousSong() {
        mediaController?.seekToPrevious()
    }
}
