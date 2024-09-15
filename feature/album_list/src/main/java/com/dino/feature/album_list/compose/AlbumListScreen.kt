package com.dino.feature.album_list.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dino.core.ui.compose.Error
import com.dino.core.ui.compose.Loading
import com.dino.feature.album_list.AlbumListUiState
import com.dino.feature.album_list.AlbumListViewModel
import com.dino.feature.album_list.model.AlbumModel
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AlbumListScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumListViewModel = viewModel(),
) {
    val uiState by viewModel.uiStateStream.collectAsStateWithLifecycle()
    AlbumListScreen(
        uiState = uiState,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumListScreen(
    uiState: AlbumListUiState,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = ("Album List")) })
        }
    ) { paddingValues ->
        when (uiState) {
            AlbumListUiState.Empty -> {
                AlbumListEmptyScreen()
            }

            is AlbumListUiState.Error -> {
                Error(modifier = Modifier.padding(paddingValues))
            }

            AlbumListUiState.Loading -> {
                Loading(modifier = Modifier.padding(paddingValues))
            }

            is AlbumListUiState.Success -> {
                AlbumListSuccessScreen(
                    items = uiState.items,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlbumListScreenPreviewSuccess() {
    AlbumListScreen(
        uiState = AlbumListUiState.Success(
            items = List(10) {
                AlbumModel(
                    id = it.toString(),
                    title = "Album $it",
                    artist = "Artist $it",
                    fileName = null,
                )
            }.toImmutableList()
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun AlbumListScreenPreviewLoading() {
    AlbumListScreen(
        uiState = AlbumListUiState.Loading,
    )
}


@Preview(showBackground = true)
@Composable
private fun AlbumListScreenPreviewEmpty() {
    AlbumListScreen(
        uiState = AlbumListUiState.Empty,
    )
}


@Preview(showBackground = true)
@Composable
private fun AlbumListScreenPreviewError() {
    AlbumListScreen(
        uiState = AlbumListUiState.Error,
    )
}