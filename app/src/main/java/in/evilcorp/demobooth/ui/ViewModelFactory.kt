package `in`.evilcorp.demobooth.ui

import `in`.evilcorp.demobooth.domain.PhotoInteractor
import `in`.evilcorp.demobooth.ui.imagelist.PhotoListViewModel
import `in`.evilcorp.demobooth.ui.imagelist.PhotosListMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val photosInteractor: PhotoInteractor,
    private val mapper: PhotosListMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PhotoListViewModel::class.java) -> {
                PhotoListViewModel(photosInteractor, mapper) as T
            }
            else -> throw IllegalStateException("Requested model class not found: ${modelClass.simpleName}")
        }
    }
}