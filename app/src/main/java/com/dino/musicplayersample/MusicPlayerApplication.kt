package com.dino.musicplayersample

import android.app.Application
import android.content.Intent
import com.dino.common.musicplayer.controller.MusicController
import com.dino.core.domain.usecase.player.DestroyMediaControllerUseCase
import com.dino.core.util.ActivityDestroyCallback
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MusicPlayerApplication : Application() {
    @Inject
    lateinit var destroyMediaControllerUseCase: DestroyMediaControllerUseCase

    override fun onCreate() {
        super.onCreate()
        destroyMusicPlayer()
    }

    private fun destroyMusicPlayer() {
        registerActivityLifecycleCallbacks(ActivityDestroyCallback {
            destroyMediaControllerUseCase()
            stopService(Intent(this, MusicController::class.java))
        })
    }
}
