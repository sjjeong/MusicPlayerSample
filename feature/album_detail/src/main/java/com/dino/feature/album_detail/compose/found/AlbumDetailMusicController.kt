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
    onPlayClick: () -> Unit,
    onShuffleClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.padding(16.dp)) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = onPlayClick,
        ) {
            Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "")
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
private fun AlbumDetailMusicControllerPreview() {
    AlbumDetailMusicController(
        onPlayClick = {},
        onShuffleClick = {},
    )
}
