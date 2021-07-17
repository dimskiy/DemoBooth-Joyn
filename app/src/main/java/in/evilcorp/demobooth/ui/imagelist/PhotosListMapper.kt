package `in`.evilcorp.demobooth.ui.imagelist

import `in`.evilcorp.demobooth.domain.model.PhotoModel
import `in`.evilcorp.demobooth.ui.model.PhotoItemUiModel

interface PhotosListMapper {

    fun mapToViewItems(domainItems: List<PhotoModel>): List<PhotoItemUiModel>

    fun mapToViewItem(domainItem: PhotoModel): PhotoItemUiModel
}