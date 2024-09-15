package com.dino.feature.album_detail.model

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
