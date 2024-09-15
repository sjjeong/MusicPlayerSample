package com.dino.feature.album_detail.compose.found

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dino.feature.album_detail.model.SongModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AlbumDetailSongList(
    songs: ImmutableList<SongModel>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp),
    ) {
        Card(
            colors = CardDefaults.cardColors().copy(
                containerColor = Color.White
            )
        ) {
            LazyColumn {
                itemsIndexed(
                    items = songs,
                    key = { _, item -> item.id }
                ) { index, item ->
                    AlbumDetailSongListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp),
                        index = index + 1,
                        song = item,
                    )
                    if (songs.lastIndex != index) {
                        HorizontalDivider()
                    }
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun AlbumDetailSongListPreview() {
    AlbumDetailSongList(
        songs = List(10) {
            SongModel(
                id = "song:$it",
                title = "Song $it",
                fileName = null,
            )
        }.toImmutableList()
    )
}
