package com.dino.core.domain.usecase.album

import com.dino.core.domain.repository.AlbumRepository
import com.dino.core.model.AlbumDto
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val albumRepository: AlbumRepository,
) {
    suspend operator fun invoke(): List<AlbumDto> {
        return albumRepository.getAlbums()
    }
}
