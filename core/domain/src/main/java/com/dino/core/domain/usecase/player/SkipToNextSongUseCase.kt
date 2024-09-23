package com.dino.core.domain.usecase.player

import com.dino.common.musicplayer.controller.MusicController
import javax.inject.Inject

class SkipToNextSongUseCase @Inject constructor(private val musicController: MusicController) {
    operator fun invoke() {
        musicController.skipToNextSong()
    }
}
