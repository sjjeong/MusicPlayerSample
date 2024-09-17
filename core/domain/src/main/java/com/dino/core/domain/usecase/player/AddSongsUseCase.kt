package com.dino.core.domain.usecase.player

import com.dino.common.musicplayer.controller.MusicController
import com.dino.common.musicplayer.model.Song
import javax.inject.Inject

class AddSongsUseCase @Inject constructor(private val musicController: MusicController) {

    operator fun invoke(songs: List<Song>) {
        musicController.songs = songs
    }
}
