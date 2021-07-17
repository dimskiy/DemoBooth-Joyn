package `in`.evilcorp.demobooth.domain.repository

import `in`.evilcorp.demobooth.domain.model.PhotoModel
import `in`.evilcorp.demobooth.platform.apiservice.model.PhotoDto

interface PhotosDomainMapper {

    fun mapToDomain(dtoItems: List<PhotoDto>): List<PhotoModel>

}