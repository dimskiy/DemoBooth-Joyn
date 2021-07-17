package `in`.evilcorp.demobooth.ui.imagelist

import `in`.evilcorp.demobooth.domain.PhotoInteractor
import `in`.evilcorp.demobooth.domain.model.PhotoModel
import `in`.evilcorp.demobooth.ui.model.PhotoItemUiModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class PhotoListViewModelTest {

    private val photosInteractor = mock<PhotoInteractor>()
    private val mapper = mock<PhotosListMapper>()
    private lateinit var viewModel: PhotoListViewModel

    @Before
    fun setup() {
        viewModel = PhotoListViewModel(photosInteractor, mapper)
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
        val photoListItems = listOf(
            PhotoItemUiModel(
                id = 1,
                thumbnailUrl = "111222",
                fullImageUrl = "title",
                userName = "username",
                tags = emptyList(),
                viewsCount = 1,
                downloadsCount = 2,
                likesCount = 3
            )
        )

        whenever(photosInteractor.observePhotos()).thenReturn(Observable.just(photos))
        whenever(mapper.mapToViewItems(photos)).thenReturn(photoListItems)

        viewModel.observePhotos()
            .test()
            .assertValue(photoListItems)
    }

    @Test
    fun `observe photos WHEN empty`() {
        val photos = emptyList<PhotoModel>()
        val photoListItems = emptyList<PhotoItemUiModel>()

        whenever(photosInteractor.observePhotos()).thenReturn(Observable.just(photos))
        whenever(mapper.mapToViewItems(photos)).thenReturn(photoListItems)

        viewModel.observePhotos()
            .test()
            .assertValue(photoListItems)
    }
}