package com.dino.feature.album_detail.compose.found

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dino.feature.album_detail.R

@Composable
fun AlbumDetailMusicController(
    isPlaying: Boolean,
    onPlayClick: () -> Unit,
    onShuffleClick: () -> Unit,
    onPauseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.padding(16.dp)) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = if (isPlaying) onPauseClick else onPlayClick,
        ) {
            if (isPlaying) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_pause_24),
                    contentDescription = ""
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            modifier = Modifier.weight(1f),
            onClick = onShuffleClick,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_shuffle_24),
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlbumDetailMusicControllerNoPlayPreview() {
    AlbumDetailMusicController(
        isPlaying = false,
        onPlayClick = {},
        onShuffleClick = {},
        onPauseClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun AlbumDetailMusicControllerPlayPreview() {
    AlbumDetailMusicController(
        isPlaying = true,
        onPlayClick = {},
        onShuffleClick = {},
        onPauseClick = {},
    )
}
