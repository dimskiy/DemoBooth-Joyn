package `in`.evilcorp.demobooth.platform.apiservice.model

import com.google.gson.annotations.SerializedName

data class PhotoResultDto(
    @SerializedName("hits") val hits: List<PhotoDto>
)