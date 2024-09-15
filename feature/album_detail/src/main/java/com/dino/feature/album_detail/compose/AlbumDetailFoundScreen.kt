package com.dino.feature.album_detail.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.dino.core.util.Mp3AlbumArtExtractor
import com.dino.feature.album_detail.compose.found.AlbumDetailInfo
import com.dino.feature.album_detail.compose.found.AlbumDetailMusicController
import com.dino.feature.album_detail.compose.found.AlbumDetailSongList
import com.dino.feature.album_detail.model.AlbumDetailModel
import com.dino.feature.album_detail.model.SongModel
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AlbumDetailFoundScreen(
    album: AlbumDetailModel,
    onPlayClick: () -> Unit,
    onShuffleClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val bitmap = Mp3AlbumArtExtractor.getAlbumArtFromRaw(context, album.fileName)?.asImageBitmap()
    Column(modifier = modifier) {
        AlbumDetailInfo(
            title = album.title,
            artist = album.artist,
            bitmap = bitmap
        )
        HorizontalDivider()
        AlbumDetailMusicController(
            onPlayClick = onPlayClick,
            onShuffleClick = onShuffleClick,
        )
        AlbumDetailSongList(
            songs = album.songs
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun AlbumDetailFoundScreenPreview() {
    AlbumDetailFoundScreen(
        album = AlbumDetailModel(
            title = "Album 1",
            artist = "Artist 1",
            fileName = null,
            songs = List(10) {
                SongModel(
                    id = "song:$it",
                    title = "Song $it",
                    fileName = null,
                )
            }.toImmutableList()
        ),
        onPlayClick = {},
        onShuffleClick = {},
    )
}
