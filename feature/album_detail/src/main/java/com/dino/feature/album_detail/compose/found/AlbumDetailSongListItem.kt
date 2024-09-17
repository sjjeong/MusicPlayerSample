package com.dino.feature.album_detail.compose.found

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dino.feature.album_detail.model.SongModel

@Composable
fun AlbumDetailSongListItem(
    onClick: (SongModel) -> Unit,
    index: Int,
    song: SongModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable { onClick(song) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = index.toString())
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = song.title,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AlbumDetailSongListItemPreview() {
    AlbumDetailSongListItem(
        onClick = {},
        index = 0,
        song = SongModel(
            id = "song:0",
            title = "Song 0",
            fileName = null,
        ),
    )
}
