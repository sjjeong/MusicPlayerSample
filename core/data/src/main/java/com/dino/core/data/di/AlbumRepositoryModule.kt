package com.dino.core.data.di

import com.dino.core.data.repository.AlbumRepositoryImpl
import com.dino.core.domain.repository.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AlbumRepositoryModule {

    @Binds
    @Singleton
    fun bindsAlbumRepository(
        albumRepositoryImpl: AlbumRepositoryImpl,
    ): AlbumRepository
}
