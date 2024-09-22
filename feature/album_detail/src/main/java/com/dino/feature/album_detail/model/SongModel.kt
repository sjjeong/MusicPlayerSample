package com.dino.feature.album_detail.model

import com.dino.common.musicplayer.model.Song
import com.dino.core.model.SongDto

data class SongModel(
    val id: String,
    val title: String,
    val fileName: String?,
)

fun SongDto.toUiModel() = SongModel(
    id = id,
    title = title,
    fileName = fileName,
)

fun SongModel.toSong(
    albumTitle: String,
    artist: String,
): Song {
    return Song(
        mediaId = id,
        title = title,
        artist = artist,
        albumTitle = albumTitle,
        fileName = fileName,
    )
}
