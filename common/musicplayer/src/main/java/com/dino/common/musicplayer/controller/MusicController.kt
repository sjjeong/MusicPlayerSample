package com.dino.common.musicplayer.controller

import com.dino.common.musicplayer.model.MediaEvent
import com.dino.common.musicplayer.model.Song
import kotlinx.coroutines.flow.Flow

interface MusicController {
    val mediaEventStream: Flow<MediaEvent>

    var songs: List<Song>

    val currentPosition: Long

    val currentSong: Song?

    fun play(mediaItemIndex: Int)

    fun resume()

    fun pause()

    fun destroy()

    fun skipToNextSong()

    fun skipToPreviousSong()

    fun seekTo(position: Long)

    fun setVolume(volume: Float)
}
