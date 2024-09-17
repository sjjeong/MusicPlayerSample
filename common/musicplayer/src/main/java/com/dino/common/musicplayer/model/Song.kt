package com.dino.common.musicplayer.model

import android.content.ContentResolver
import android.content.Context
import androidx.media3.common.MediaItem


sealed class Song {
    abstract val mediaId: String
    abstract val title: String
    abstract val artist: String
    abstract val albumTitle: String

    abstract fun toUri(context: Context): android.net.Uri?

    data class Raw(
        override val mediaId: String,
        override val title: String,
        override val artist: String,
        override val albumTitle: String,
        val fileName: String?,
    ) : Song() {
        override fun toUri(context: Context): android.net.Uri? {
            val rawResourceId = context.resources.getIdentifier(fileName, "raw", context.packageName)
            return android.net.Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .path(rawResourceId.toString())
                .build()
        }
    }

    data class Uri(
        override val mediaId: String,
        override val title: String,
        override val artist: String,
        override val albumTitle: String,
        val mediaUri: android.net.Uri?,
    ) : Song() {
        override fun toUri(context: Context): android.net.Uri? {
            return mediaUri
        }
    }
}

fun MediaItem.toSong(): Song {
    return Song.Uri(
        mediaId = mediaId,
        mediaUri = requestMetadata.mediaUri,
        title = mediaMetadata.title.toString(),
        artist = mediaMetadata.artist.toString(),
        albumTitle = mediaMetadata.albumTitle.toString(),
    )
}
