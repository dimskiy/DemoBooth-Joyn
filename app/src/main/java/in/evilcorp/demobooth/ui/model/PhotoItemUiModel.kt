package `in`.evilcorp.demobooth.ui.model

import `in`.evilcorp.demobooth.commonshared.FilterableRecycleAdapter
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoItemUiModel(
    val id: Int,
    val thumbnailUrl: String?,
    val fullImageUrl: String?,
    val userName: String?,
    val tags: List<String>,
    val viewsCount: Int?,
    val downloadsCount: Int?,
    val likesCount: Int?,
) : FilterableRecycleAdapter.Item(), Parcelable {

    override fun isIdEqual(other: FilterableRecycleAdapter.Item): Boolean =
        id == (other as? PhotoItemUiModel)?.id

    override fun isContentEqual(other: FilterableRecycleAdapter.Item): Boolean = this == other
}