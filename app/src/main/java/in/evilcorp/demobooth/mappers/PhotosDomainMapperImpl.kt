package `in`.evilcorp.demobooth.mappers

import `in`.evilcorp.demobooth.domain.model.PhotoModel
import `in`.evilcorp.demobooth.domain.repository.PhotosDomainMapper
import `in`.evilcorp.demobooth.platform.apiservice.model.PhotoDto
import javax.inject.Inject

class PhotosDomainMapperImpl @Inject constructor(): PhotosDomainMapper {

    override fun mapToDomain(dtoItems: List<PhotoDto>): List<PhotoModel> =
        dtoItems.map(this::mapToDomain)

    private fun mapToDomain(dto: PhotoDto): PhotoModel =
        PhotoModel(
            id = dto.id,
            previewUrl = dto.previewUrl,
            fullImageUrl = dto.fullImageUrl,
            userName = dto.userName,
            tags = dto.tags.orEmpty(),
            viewsCount = dto.viewsCount,
            downloadsCount = dto.downloadsCount,
            likesCount = dto.likesCount
        )
}