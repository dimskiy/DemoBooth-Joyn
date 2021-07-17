package `in`.evilcorp.demobooth.domain.model

data class PhotoModel(
    val id: Int,
    val previewUrl: String?,
    val fullImageUrl: String?,
    val userName: String?,
    val tags: List<String>,
    val viewsCount: Int?,
    val downloadsCount: Int?,
    val likesCount: Int?
)