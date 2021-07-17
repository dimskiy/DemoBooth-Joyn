package `in`.evilcorp.demobooth.domain

import `in`.evilcorp.demobooth.BaseRxTest
import `in`.evilcorp.demobooth.domain.model.PhotoModel
import `in`.evilcorp.demobooth.domain.repository.PhotoRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class PhotoInteractorImplTest : BaseRxTest() {

    private val repository = mock<PhotoRepository>()
    private lateinit var interactor: PhotoInteractor

    @Before
    fun setup() {
        interactor = PhotoInteractorImpl(repository)
    }

    @Test
    fun `observe photos`() {
        val photos = listOf(
            PhotoModel(
                id = 1,
                previewUrl = "111222",
                fullImageUrl = "title",
                userName = "username",
                tags = emptyList(),
                viewsCount = 1,
                downloadsCount = 2,
                likesCount = 3
            )
        )

        whenever(repository.observePhotos()).thenReturn(Observable.just(photos))

        interactor.observePhotos()
            .test()
            .assertValue(photos)
    }

    @Test
    fun `observe photos when empty`() {
        val photos = emptyList<PhotoModel>()

        whenever(repository.observePhotos()).thenReturn(Observable.just(photos))

        interactor.observePhotos()
            .test()
            .assertValue(photos)
    }
}