package com.dino.common.musicplayer.model

data class MediaEvent(
    val playerState: PlayerState,
    val currentSong: Song?,
    val currentPosition: Long,
    val totalDuration: Long,
    val isShuffleEnabled: Boolean,
    val isRepeatOneEnabled: Boolean,
) {

    companion object {
        val EMPTY = MediaEvent(
            playerState = PlayerState.STOPPED,
            currentSong = null,
            currentPosition = 0L,
            totalDuration = 0L,
            isShuffleEnabled = false,
            isRepeatOneEnabled = false,
        )
    }
}
