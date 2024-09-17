package com.dino.core.domain.usecase.album

import com.dino.core.domain.repository.AlbumRepository
import com.dino.core.model.AlbumDto
import javax.inject.Inject

class GetAlbumUseCase @Inject constructor(
    private val albumRepository: AlbumRepository,
) {
    suspend operator fun invoke(id: String): AlbumDto? {
        return albumRepository.getAlbum(id = id)
    }
}
