package `in`.evilcorp.demobooth.mappers

import `in`.evilcorp.demobooth.domain.model.PhotoModel
import `in`.evilcorp.demobooth.ui.imagelist.PhotosListMapper
import `in`.evilcorp.demobooth.ui.model.PhotoItemUiModel
import javax.inject.Inject

class PhotosListMapperImpl @Inject constructor(
) : PhotosListMapper {

    override fun mapToViewItems(domainItems: List<PhotoModel>): List<PhotoItemUiModel> =
        domainItems.map(this::mapToViewItem)

    override fun mapToViewItem(domainItem: PhotoModel): PhotoItemUiModel =
        PhotoItemUiModel(
            id = domainItem.id,
            thumbnailUrl = domainItem.previewUrl,
            fullImageUrl = domainItem.fullImageUrl,
            userName = domainItem.userName,
            tags = domainItem.tags,
            viewsCount = domainItem.viewsCount,
            downloadsCount = domainItem.downloadsCount,
            likesCount = domainItem.likesCount
        )
}