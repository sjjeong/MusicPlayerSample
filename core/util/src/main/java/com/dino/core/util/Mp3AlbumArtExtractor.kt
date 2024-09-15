package com.dino.core.util

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever

object Mp3AlbumArtExtractor {
    fun getAlbumArtFromRaw(context: Context, fileName: String?): Bitmap? {
        if (fileName == null) return null
        val resourceId = context.resources.getIdentifier(fileName, "raw", context.packageName)
        return getAlbumArtFromRaw(
            context = context,
            rawResourceId = resourceId
        )

    }

    fun getAlbumArtFromRaw(context: Context, rawResourceId: Int?): Bitmap? {
        if (rawResourceId == null) return null
        val retriever = MediaMetadataRetriever()
        try {
            // res/raw 폴더에서 파일을 가져오기 위해 AssetFileDescriptor 사용
            val assetFileDescriptor: AssetFileDescriptor = context.resources.openRawResourceFd(rawResourceId)
            retriever.setDataSource(
                assetFileDescriptor.fileDescriptor,
                assetFileDescriptor.startOffset,
                assetFileDescriptor.length
            )

            // 앨범 아트 가져오기
            val art = retriever.embeddedPicture
            retriever.release()

            return if (art != null) {
                BitmapFactory.decodeByteArray(art, 0, art.size)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
