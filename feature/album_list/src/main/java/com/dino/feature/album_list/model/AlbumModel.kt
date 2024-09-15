package com.dino.feature.album_list.model

import android.net.Uri
import androidx.core.net.toUri
import com.dino.core.model.AlbumDto

data class AlbumModel(
    val id: String,
    val title: String,
    val artist: String,
    val fileName: String?,
    val uri: Uri,
)

fun AlbumDto.toUiModel() = AlbumModel(
    id = id,
    title = title,
    artist = artist,
    fileName = songs.firstOrNull()?.fileName,
    uri = "musicplayer://album/detail".toUri()
        .buildUpon()
        .appendQueryParameter("albumId", id)
        .build()
)
