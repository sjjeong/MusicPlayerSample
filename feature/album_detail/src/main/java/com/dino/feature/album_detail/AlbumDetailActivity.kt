package com.dino.feature.album_detail

import androidx.compose.runtime.Composable
import com.dino.core.ui.base.BaseActivity
import com.dino.feature.album_detail.compose.AlbumDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailActivity : BaseActivity() {
    override val content: @Composable () -> Unit = {
        AlbumDetailScreen(
            onPlayClick = {
                // TODO: Play
            },
            onShuffleClick = {
                // TODO: Shuffle Play
            },
        )
    }
}
