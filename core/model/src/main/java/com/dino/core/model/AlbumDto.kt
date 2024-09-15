package com.dino.core.model

data class AlbumDto(
    val id: String,
    val title: String,
    val artist: String,
    val songs: List<SongDto>
)
