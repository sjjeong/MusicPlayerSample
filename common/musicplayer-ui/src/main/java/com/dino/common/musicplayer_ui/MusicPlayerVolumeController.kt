package com.dino.common.musicplayer_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MusicPlayerVolumeController(
    volume: Float,
    onVolumeChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    space: Dp = 8.dp,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = space)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_volume_off_24),
            contentDescription = ""
        )
        Slider(
            modifier = Modifier.weight(1f),
            value = volume,
            onValueChange = onVolumeChange,
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_volume_up_24),
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MusicPlayerVolumeControllerPreview() {
    MusicPlayerVolumeController(
        volume = 0.5f,
        onVolumeChange = {},
    )
}
