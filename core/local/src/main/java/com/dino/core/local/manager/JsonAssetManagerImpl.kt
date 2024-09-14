package com.dino.core.local.manager

import android.content.res.AssetManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JsonAssetManagerImpl(
    private val assetManager: AssetManager,
) : JsonAssetManager {

    override suspend fun loadString(fileName: String): String = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            val inputStream = assetManager.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        }.getOrDefault("")
    }

}
