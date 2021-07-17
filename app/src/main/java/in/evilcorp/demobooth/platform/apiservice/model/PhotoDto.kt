package `in`.evilcorp.demobooth.platform.apiservice.model

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("id") val id: Int,
    @SerializedName("previewURL") val previewUrl: String?,
    @SerializedName("imageURL") val fullImageUrl: String?,
    @SerializedName("user") val userName: String?,
    @SerializedName("tags") val tags: List<String>?,
    @SerializedName("views") val viewsCount: Int?,
    @SerializedName("downloads") val downloadsCount: Int?,
    @SerializedName("likes") val likesCount: Int?
)