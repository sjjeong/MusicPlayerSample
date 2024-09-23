package com.dino.common.musicplayer_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MusicPlayerController(
    isPlaying: Boolean,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onSkipToPreviousSong: () -> Unit,
    onSkipToNextSong: () -> Unit,
    modifier: Modifier = Modifier,
    space: Dp = 8.dp,
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = space)
    ) {
        IconButton(onClick = onSkipToPreviousSong) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_skip_previous_24),
                contentDescription = ""
            )
        }

        IconButton(onClick = if (isPlaying) onPause else onResume) {
            Icon(
                painter = painterResource(id = if (isPlaying) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24),
                contentDescription = ""
            )
        }

        IconButton(onClick = onSkipToNextSong) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_skip_next_24),
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MusicPlayerControllerPreview() {
    MusicPlayerController(
        isPlaying = true,
        onPause = {},
        onResume = {},
        onSkipToPreviousSong = {},
        onSkipToNextSong = {},
    )
}
