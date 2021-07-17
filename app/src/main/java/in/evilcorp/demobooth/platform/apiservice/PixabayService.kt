package `in`.evilcorp.demobooth.platform.apiservice

import `in`.evilcorp.demobooth.platform.apiservice.model.PhotoDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {

    @GET(".")
    fun findPhotos(
        @Query("key") apiKey: String,
        @Query("q") search: String
    ): Single<List<PhotoDto>>

    companion object {
        const val API_KEY_LABEL = "API_KEY"
    }
}