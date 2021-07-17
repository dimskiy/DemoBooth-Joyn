package `in`.evilcorp.demobooth.domain.repository

import `in`.evilcorp.demobooth.domain.model.PhotoModel
import io.reactivex.Completable
import io.reactivex.Observable

interface PhotoRepository {

    fun observePhotos(): Observable<List<PhotoModel>>

    fun fetchPhotos(query: String): Completable

}