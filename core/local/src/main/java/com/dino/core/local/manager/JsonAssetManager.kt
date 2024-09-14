package com.dino.core.local.manager

interface JsonAssetManager {
    suspend fun loadString(fileName: String): String
}
