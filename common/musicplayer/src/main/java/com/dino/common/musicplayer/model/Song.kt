package com.dino.common.musicplayer.model

import android.content.ContentResolver
import android.content.Context


data class Song(
    val mediaId: String,
    val title: String,
    val artist: String,
    val albumTitle: String,
    val fileName: String?,
) {
    fun toUri(context: Context): android.net.Uri? {
        val rawResourceId = context.resources.getIdentifier(fileName, "raw", context.packageName)
        return android.net.Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .path(rawResourceId.toString())
            .build()
    }
}
