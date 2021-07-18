package `in`.evilcorp.demobooth.domain.repository

import `in`.evilcorp.demobooth.domain.model.PhotoModel
import `in`.evilcorp.demobooth.platform.apiservice.model.PhotoResultDto

interface PhotosDomainMapper {

    fun mapToDomain(result: PhotoResultDto): List<PhotoModel>

}