package com.dino.core.local.model

import com.dino.core.model.AlbumDto
import kotlinx.serialization.Serializable

@Serializable
data class AlbumLocal(
    val id: String,
    val title: String,
    val artist: String,
    val songs: List<SongLocal>
)

fun AlbumLocal.toDto(): AlbumDto {
    return AlbumDto(
        id = id,
        title = title,
        artist = artist,
        songs = songs.map { it.toDto() }
    )
}
