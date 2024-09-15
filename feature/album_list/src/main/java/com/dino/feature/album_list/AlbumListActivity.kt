package com.dino.feature.album_list

import androidx.compose.runtime.Composable
import com.dino.core.ui.base.BaseActivity
import com.dino.feature.album_list.compose.AlbumListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumListActivity : BaseActivity() {
    override val content: @Composable () -> Unit = {
        AlbumListScreen()
    }
}
