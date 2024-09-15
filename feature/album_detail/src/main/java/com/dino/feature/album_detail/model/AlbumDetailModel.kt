package com.dino.feature.album_detail.model

import com.dino.core.model.AlbumDto
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class AlbumDetailModel(
    val title: String,
    val artist: String,
    val fileName: String?,
    val songs: ImmutableList<SongModel>,
)

fun AlbumDto.toUiModel() = AlbumDetailModel(
    title = title,
    artist = artist,
    fileName = songs.firstOrNull()?.fileName,
    songs = songs.map { it.toUiModel() }.toImmutableList(),
)
