package `in`.evilcorp.demobooth.domain

import `in`.evilcorp.demobooth.domain.model.PhotoModel
import `in`.evilcorp.demobooth.domain.repository.PhotoRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class PhotoInteractorImpl @Inject constructor(
    private val repository: PhotoRepository
) : PhotoInteractor {

    override fun observePhotos(): Observable<List<PhotoModel>> = repository.observePhotos()

    override fun onSearchUpdate(query: String): Completable = repository.fetchPhotos(query)
}