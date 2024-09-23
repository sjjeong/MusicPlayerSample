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
    volume: Float,
    isPlaying: Boolean,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onSkipToPreviousSong: () -> Unit,
    onSkipToNextSong: () -> Unit,
    onVolumeChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var isShowModal by remember { mutableStateOf(false) }
    val albumArt by remember(song.fileName) {
        mutableStateOf(Mp3AlbumArtExtractor.getAlbumArtFromRaw(context, song.fileName)?.asImageBitmap())
    }

    MusicPlayer(
        isShowModal = isShowModal,
        title = song.title,
        artist = song.artist,
        albumArt = albumArt,
        progress = progress,
        volume = volume,
        isPlaying = isPlaying,
        onPause = onPause,
        onResume = onResume,
        onSkipToPreviousSong = onSkipToPreviousSong,
        onSkipToNextSong = onSkipToNextSong,
        onVolumeChange = onVolumeChange,
        onBannerClick = { isShowModal = true },
        onMusicPlayerModalHide = { isShowModal = false },
        modifier = modifier,
    )
}

@Composable
private fun MusicPlayer(
    isShowModal: Boolean,
    title: String,
    artist: String,
    albumArt: ImageBitmap?,
    progress: Float,
    isPlaying: Boolean,
    volume: Float,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onSkipToPreviousSong: () -> Unit,
    onSkipToNextSong: () -> Unit,
    onVolumeChange: (Float) -> Unit,
    onBannerClick: () -> Unit,
    onMusicPlayerModalHide: () -> Unit,
    modifier: Modifier = Modifier,
) {

    if (isShowModal) {
        MusicPlayerModal(
            title = title,
            artist = artist,
            albumArt = albumArt,
            progress = progress,
            volume = volume,
            isPlaying = isPlaying,
            onPause = onPause,
            onResume = onResume,
            onSkipToPreviousSong = onSkipToPreviousSong,
            onSkipToNextSong = onSkipToNextSong,
            onVolumeChange = onVolumeChange,
            onHide = onMusicPlayerModalHide,
        )
    }
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
