package `in`.evilcorp.demobooth.ui.imagelist

import `in`.evilcorp.demobooth.domain.PhotoInteractor
import `in`.evilcorp.demobooth.ui.UserMessageTypes
import `in`.evilcorp.demobooth.ui.model.PhotoItemUiModel
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class PhotoListViewModel(
    private val photosInteractor: PhotoInteractor,
    private val mapper: PhotosListMapper
): ViewModel() {

    private val photoClickSubject = PublishSubject.create<PhotoItemUiModel>()
    private val userMessagesSubject = PublishSubject.create<UserMessageTypes>()
    private val showProgressSubject = BehaviorSubject.createDefault(false)

    fun observePhotos(): Observable<List<PhotoItemUiModel>> =
        photosInteractor.observePhotos()
            .doOnEach { showProgressSubject.onNext(false) }
            .map(mapper::mapToViewItems)

    fun observePhotoDetailsRequested(): Observable<PhotoItemUiModel> = photoClickSubject

    fun observeUserNotifications(): Observable<UserMessageTypes> = userMessagesSubject

    fun observeShowProgress(): Observable<Boolean> = showProgressSubject

    fun onItemClick(item: PhotoItemUiModel): Completable = Completable.fromAction {
        photoClickSubject.onNext(item)
    }

    fun onSearchQueryChange(query: CharSequence): Completable =
        if (query.length >= MIN_QUERY_LENGTH) {
            photosInteractor.onSearchUpdate(query.toString())
                .doOnSubscribe { showProgressSubject.onNext(true) }
                .doOnError { showProgressSubject.onNext(false) }
                .onErrorComplete { error ->
                    userMessagesSubject.onNext(UserMessageTypes.ERROR_FETCHING_IMAGES)
                    true
                }
        } else {
            userMessagesSubject.onNext(UserMessageTypes.QUERY_TOO_SHORT)
            Completable.complete()
        }

    companion object {
        private const val MIN_QUERY_LENGTH = 3
    }
}