package com.dino.core.local.model

import com.dino.core.model.SongDto
import kotlinx.serialization.Serializable

@Serializable
data class SongLocal(
    val id: String,
    val title: String,
)

fun SongLocal.toDto(): SongDto {
    return SongDto(
        id = id,
        title = title,
    )
}
