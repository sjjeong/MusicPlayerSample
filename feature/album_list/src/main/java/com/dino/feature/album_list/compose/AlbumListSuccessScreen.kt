package com.dino.feature.album_list.compose

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dino.feature.album_list.model.AlbumModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AlbumListSuccessScreen(
    items: ImmutableList<AlbumModel>,
    onAlbumClick: (Uri) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        LazyVerticalGrid(
            modifier = Modifier.padding(16.dp),
            columns = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = items,
                key = { it.id }
            ) { item ->
                AlbumListItem(
                    album = item,
                    onClick = onAlbumClick
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun AlbumListSuccessScreenPreview() {
    AlbumListSuccessScreen(
        items = List(10) {
            AlbumModel(
                id = it.toString(),
                title = "Album $it",
                artist = "Artist $it",
                fileName = null,
                uri = Uri.EMPTY,
            )
        }.toImmutableList(),
        onAlbumClick = {},
    )
}
