package com.dino.common.musicplayer_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MusicPlayerModal(
    title: String,
    artist: String,
    albumArt: ImageBitmap?,
    progress: Float,
    volume: Float,
    isPlaying: Boolean,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onSkipToPreviousSong: () -> Unit,
    onSkipToNextSong: () -> Unit,
    onVolumeChange: (Float) -> Unit,
    onHide: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onHide,
        sheetState = sheetState,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        scope
                            .launch {
                                sheetState.hide()
                            }
                            .invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onHide()
                                }
                            }
                    }
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = title)
                Text(text = artist)
            }
            HorizontalDivider()
            if (albumArt != null) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .padding(24.dp),
                    bitmap = albumArt,
                    contentDescription = ""
                )
            }

            MusicPlayerController(
                isPlaying = isPlaying,
                onPause = onPause,
                onResume = onResume,
                onSkipToPreviousSong = onSkipToPreviousSong,
                onSkipToNextSong = onSkipToNextSong,
            )

            Spacer(modifier = Modifier.height(16.dp))

            MusicPlayerVolumeController(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                volume = volume,
                onVolumeChange = onVolumeChange,
            )

            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = { progress },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MusicPlayerFullPreview() {
    MusicPlayerModal(
        title = "title",
        artist = "artist",
        albumArt = null,
        progress = 0.9f,
        volume = 0.5f,
        isPlaying = true,
        onPause = {},
        onResume = {},
        onSkipToPreviousSong = {},
        onSkipToNextSong = {},
        onHide = {},
        onVolumeChange = {},
    )
}
