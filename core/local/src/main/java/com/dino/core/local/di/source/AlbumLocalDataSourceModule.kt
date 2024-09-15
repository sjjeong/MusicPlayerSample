package com.dino.core.local.di.source

import com.dino.core.data.source.local.AlbumLocalDataSource
import com.dino.core.local.source.AlbumLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AlbumLocalDataSourceModule {

    @Binds
    @Singleton
    fun bindsAlbumLocalDataSource(
        albumLocalDataSourceImpl: AlbumLocalDataSourceImpl,
    ): AlbumLocalDataSource
}
