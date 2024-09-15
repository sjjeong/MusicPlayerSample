package com.dino.core.domain.repository

import com.dino.core.model.AlbumDto

interface AlbumRepository {
    suspend fun getAlbums(): List<AlbumDto>
}
