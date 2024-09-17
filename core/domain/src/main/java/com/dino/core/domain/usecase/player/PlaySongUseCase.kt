package com.dino.core.domain.usecase.player

import com.dino.common.musicplayer.controller.MusicController
import javax.inject.Inject

class PlaySongUseCase @Inject constructor(private val musicController: MusicController) {
    operator fun invoke(mediaItemIndex: Int) {
        musicController.play(mediaItemIndex)
    }
}
