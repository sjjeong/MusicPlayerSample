package com.dino.core.data.repository

import com.dino.core.data.source.local.AlbumLocalDataSource
import com.dino.core.domain.repository.AlbumRepository
import com.dino.core.model.AlbumDto
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumLocalDataSource: AlbumLocalDataSource,
) : AlbumRepository {
    override suspend fun getAlbums(): List<AlbumDto> {
        return albumLocalDataSource.getAlbums()
    }
}
