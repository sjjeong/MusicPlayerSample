package com.dino.feature.album_detail.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.dino.common.musicplayer.model.Song
import com.dino.common.musicplayer.util.Mp3AlbumArtExtractor
import com.dino.common.musicplayer_ui.MusicPlayer
import com.dino.feature.album_detail.compose.found.AlbumDetailInfo
import com.dino.feature.album_detail.compose.found.AlbumDetailMusicController
import com.dino.feature.album_detail.compose.found.AlbumDetailSongList
import com.dino.feature.album_detail.model.AlbumDetailModel
import com.dino.feature.album_detail.model.SongModel
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AlbumDetailFoundScreen(
    album: AlbumDetailModel,
    currentSong: Song?,
    isPlaying: Boolean,
    isShowingPlayer: Boolean,
    progress: Float,
    onPlayClick: () -> Unit,
    onShuffleClick: () -> Unit,
    onPlaySongClick: (SongModel) -> Unit,
    onPauseClick: () -> Unit,
    onResumeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val bitmap = Mp3AlbumArtExtractor.getAlbumArtFromRaw(context, album.fileName)?.asImageBitmap()
    Box(modifier = modifier) {
        Column {
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
                songs = album.songs,
                onPlaySongClick = onPlaySongClick,
            )
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
private fun AlbumDetailFoundScreenNoPlayPreview() {
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
        currentSong = null,
        isPlaying = false,
        isShowingPlayer = false,
        progress = 0.9f,
        onPlayClick = {},
        onShuffleClick = {},
        onPlaySongClick = {},
        onPauseClick = {},
        onResumeClick = {},
    )
}


@Preview(showBackground = true)
@Composable
private fun AlbumDetailFoundScreenPlayingPreview() {
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
        currentSong = null,
        isPlaying = true,
        isShowingPlayer = true,
        progress = 0.9f,
        onPlayClick = {},
        onShuffleClick = {},
        onPlaySongClick = {},
        onPauseClick = {},
        onResumeClick = {},
    )
}
