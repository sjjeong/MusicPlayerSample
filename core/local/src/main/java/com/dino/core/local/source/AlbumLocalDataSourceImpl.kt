package com.dino.core.local.source

import com.dino.core.data.source.local.AlbumLocalDataSource
import com.dino.core.local.manager.JsonAssetManager
import com.dino.core.local.model.AlbumLocal
import com.dino.core.local.model.toDto
import com.dino.core.model.AlbumDto
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AlbumLocalDataSourceImpl @Inject constructor(
    private val jsonAssetManager: JsonAssetManager,
) : AlbumLocalDataSource {
    override suspend fun getAlbums(): List<AlbumDto> {
        val jsonString = jsonAssetManager.loadString("albums.json")
        return Json.decodeFromString<List<AlbumLocal>>(jsonString)
            .map { it.toDto() }
    }
}
