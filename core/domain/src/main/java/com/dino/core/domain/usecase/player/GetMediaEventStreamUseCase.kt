package com.dino.core.domain.usecase.player

import com.dino.common.musicplayer.controller.MusicController
import com.dino.common.musicplayer.model.MediaEvent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMediaEventStreamUseCase @Inject constructor(private val musicController: MusicController) {

    operator fun invoke(): Flow<MediaEvent> {
        return musicController.mediaEventStream
    }
}
