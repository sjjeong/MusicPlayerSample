package com.dino.common.musicplayer_ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import com.dino.common.musicplayer.model.Song
import com.dino.common.musicplayer.util.Mp3AlbumArtExtractor

@Composable
fun MusicPlayer(
    song: Song,
    progress: Float,
    isPlaying: Boolean,
    onPause: () -> Unit,
    onResume: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var isExpanded by remember { mutableStateOf(false) }
    val albumArt by remember(song.fileName) {
        mutableStateOf(Mp3AlbumArtExtractor.getAlbumArtFromRaw(context, song.fileName)?.asImageBitmap())
    }

    MusicPlayer(
        isExpanded = isExpanded,
        title = song.title,
        artist = song.artist,
        albumArt = albumArt,
        progress = progress,
        isPlaying = isPlaying,
        onPause = onPause,
        onResume = onResume,
        onBannerClick = { isExpanded = true },
        onFullClick = { isExpanded = false },
        modifier = modifier,
    )
}

@Composable
private fun MusicPlayer(
    isExpanded: Boolean,
    title: String,
    artist: String,
    albumArt: ImageBitmap?,
    progress: Float,
    isPlaying: Boolean,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onBannerClick: () -> Unit,
    onFullClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    if (isExpanded) {
        MusicPlayerFull(
            modifier = modifier,
            onClick = onFullClick,
        )
    } else {
        MusicPlayerBanner(
            modifier = modifier,
            title = title,
            artist = artist,
            albumArt = albumArt,
            progress = progress,
            isPlaying = isPlaying,
            onClick = onBannerClick,
            onPause = onPause,
            onResume = onResume,
        )
    }
}
