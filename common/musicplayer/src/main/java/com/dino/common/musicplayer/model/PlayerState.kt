package com.dino.common.musicplayer.model

import androidx.media3.common.Player

enum class PlayerState {
    PLAYING,
    PAUSED,
    STOPPED
}

internal fun Int.toPlayerState(isPlaying: Boolean): PlayerState {
    return when (this) {
        Player.STATE_IDLE -> PlayerState.STOPPED
        Player.STATE_ENDED -> PlayerState.STOPPED
        else -> if (isPlaying) PlayerState.PLAYING else PlayerState.PAUSED
    }
}
