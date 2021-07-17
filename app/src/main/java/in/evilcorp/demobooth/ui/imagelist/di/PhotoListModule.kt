package `in`.evilcorp.demobooth.ui.imagelist.di

import `in`.evilcorp.demobooth.mappers.PhotosListMapperImpl
import `in`.evilcorp.demobooth.ui.ViewModelFactory
import `in`.evilcorp.demobooth.ui.imagelist.PhotosListMapper
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class PhotoListModule {

    @Binds
    @PhotoListActivityScope
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @PhotoListActivityScope
    abstract fun bindPhotosListMapper(mapper: PhotosListMapperImpl): PhotosListMapper

}