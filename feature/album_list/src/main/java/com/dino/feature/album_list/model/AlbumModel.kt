package com.dino.feature.album_list.model

import com.dino.core.model.AlbumDto

data class AlbumModel(
    val id: String,
    val title: String,
    val artist: String,
    val fileName: String?,
)

fun AlbumDto.toUiModel() = AlbumModel(
    id = id,
    title = title,
    artist = artist,
    fileName = songs.firstOrNull()?.fileName,
)
