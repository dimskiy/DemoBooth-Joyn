package `in`.evilcorp.demobooth.domain.repository

import `in`.evilcorp.demobooth.domain.model.PhotoModel
import `in`.evilcorp.demobooth.platform.apiservice.PixabayService
import `in`.evilcorp.demobooth.platform.apiservice.PixabayService.Companion.API_KEY_LABEL
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Named

class PhotoRepositoryImpl @Inject constructor(
    private val pixabayService: PixabayService,
    private val mapper: PhotosDomainMapper,
    @Named(API_KEY_LABEL) private val apiKey: String
) : PhotoRepository {

    private val photosResponseCache = BehaviorSubject.createDefault(emptyList<PhotoModel>())

    override fun observePhotos(): Observable<List<PhotoModel>> = photosResponseCache

    override fun fetchPhotos(query: String): Completable =
        pixabayService.findPhotos(apiKey, query)
            .subscribeOn(Schedulers.io())
            .map(mapper::mapToDomain)
            .doOnSuccess(photosResponseCache::onNext)
            .ignoreElement()
}