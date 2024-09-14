package com.dino.core.local.di

import android.content.Context
import com.dino.core.local.manager.JsonAssetManager
import com.dino.core.local.manager.JsonAssetManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JsonAssetManagerModule {

    @Provides
    @Singleton
    fun provideJsonAssetManager(
        @ApplicationContext context: Context,
    ): JsonAssetManager {
        return JsonAssetManagerImpl(assetManager = context.assets)
    }

}
