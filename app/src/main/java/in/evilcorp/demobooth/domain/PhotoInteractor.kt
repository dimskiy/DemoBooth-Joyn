package `in`.evilcorp.demobooth.domain

import `in`.evilcorp.demobooth.domain.model.PhotoModel
import io.reactivex.Completable
import io.reactivex.Observable

interface PhotoInteractor {

    fun observePhotos(): Observable<List<PhotoModel>>

    fun onSearchUpdate(query: String): Completable

}