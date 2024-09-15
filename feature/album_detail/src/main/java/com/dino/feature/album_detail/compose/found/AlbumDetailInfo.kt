package com.dino.feature.album_detail.compose.found

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AlbumDetailInfo(
    title: String,
    artist: String,
    bitmap: ImageBitmap?,
    modifier: Modifier = Modifier,
) {

    Row(modifier = modifier.padding(16.dp)) {
        if (bitmap != null) {
            Image(
                modifier = Modifier.size(72.dp),
                bitmap = bitmap,
                contentDescription = ""
            )
        } else {
            Icon(
                modifier = Modifier.size(72.dp),
                imageVector = Icons.Filled.AccountBox,
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier.align(Alignment.CenterVertically)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = artist,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlbumDetailInfoPreview() {
    AlbumDetailInfo(
        title = "title",
        artist = "artist",
        bitmap = null
    )
}
