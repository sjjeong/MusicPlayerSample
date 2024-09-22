package com.dino.feature.album_list.compose

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dino.common.musicplayer.model.Song
import com.dino.common.musicplayer_ui.MusicPlayer
import com.dino.feature.album_list.model.AlbumModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AlbumListSuccessScreen(
    items: ImmutableList<AlbumModel>,
    currentSong: Song?,
    isPlaying: Boolean,
    isShowingPlayer: Boolean,
    progress: Float,
    onAlbumClick: (Uri) -> Unit,
    onPauseClick: () -> Unit,
    onResumeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        LazyVerticalGrid(
            modifier = Modifier.padding(16.dp),
            columns = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = items,
                key = { it.id }
            ) { item ->
                AlbumListItem(
                    album = item,
                    onClick = onAlbumClick
                )
            }
        }
        if (isShowingPlayer && currentSong != null) {
            MusicPlayer(
                modifier = Modifier.align(Alignment.BottomCenter),
                song = currentSong,
                progress = progress,
                isPlaying = isPlaying,
                onPause = onPauseClick,
                onResume = onResumeClick,
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun AlbumListSuccessScreenPreview() {
    AlbumListSuccessScreen(
        items = List(10) {
            AlbumModel(
                id = it.toString(),
                title = "Album $it",
                artist = "Artist $it",
                fileName = null,
                uri = Uri.EMPTY,
            )
        }.toImmutableList(),
        currentSong = null,
        isPlaying = false,
        isShowingPlayer = false,
        progress = 0.9f,
        onAlbumClick = {},
        onPauseClick = {},
        onResumeClick = {},
    )
}
