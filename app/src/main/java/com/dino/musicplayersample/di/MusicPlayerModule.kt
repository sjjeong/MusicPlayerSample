package com.dino.musicplayersample.di

import android.content.Context
import com.dino.common.musicplayer.controller.DefaultMusicController
import com.dino.common.musicplayer.controller.MusicController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MusicPlayerModule {

    @Provides
    @Singleton
    fun provideMusicController(
        @ApplicationContext context: Context,
    ): MusicController {
        return DefaultMusicController(context)
    }

}
