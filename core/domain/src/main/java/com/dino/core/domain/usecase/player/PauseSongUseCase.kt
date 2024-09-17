package com.dino.core.domain.usecase.player

import com.dino.common.musicplayer.controller.MusicController
import javax.inject.Inject

class PauseSongUseCase @Inject constructor(private val musicController: MusicController) {
    operator fun invoke() = musicController.pause()
}
