package com.dino.core.data.source.local

import com.dino.core.model.AlbumDto

interface AlbumLocalDataSource {
    suspend fun getAlbums(): List<AlbumDto>
    suspend fun getAlbum(id: String): AlbumDto?
}
