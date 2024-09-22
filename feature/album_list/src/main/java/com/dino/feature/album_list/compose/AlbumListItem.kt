package com.dino.feature.album_list.compose

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dino.common.musicplayer.util.Mp3AlbumArtExtractor
import com.dino.feature.album_list.model.AlbumModel

@Composable
fun AlbumListItem(
    album: AlbumModel,
    onClick: (Uri) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val bitmap = Mp3AlbumArtExtractor.getAlbumArtFromRaw(context, album.fileName)?.asImageBitmap()
    Card(
        modifier = modifier,
        onClick = {
            onClick(album.uri)
        }
    ) {
        if (bitmap != null) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                bitmap = bitmap,
                contentDescription = ""
            )
        } else {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = ""
                )
            }
        }
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = album.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = album.artist,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlbumListItemPreview() {
    AlbumListItem(
        album = AlbumModel(
            id = "",
            title = "title",
            artist = "artist",
            fileName = "adele_30_easy_on_me",
            uri = Uri.EMPTY,
        ),
        onClick = {}
    )
}
