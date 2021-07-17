package `in`.evilcorp.demobooth.di

import `in`.evilcorp.demobooth.domain.PhotoInteractor
import `in`.evilcorp.demobooth.domain.PhotoInteractorImpl
import `in`.evilcorp.demobooth.domain.repository.PhotoRepository
import `in`.evilcorp.demobooth.domain.repository.PhotoRepositoryImpl
import `in`.evilcorp.demobooth.domain.repository.PhotosDomainMapper
import `in`.evilcorp.demobooth.mappers.PhotosDomainMapperImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindPhotoInteractor(interactor: PhotoInteractorImpl): PhotoInteractor

    @Binds
    @Singleton
    abstract fun bindPhotoRepository(repository: PhotoRepositoryImpl): PhotoRepository

    @Binds
    abstract fun bindPhotosDomainMapper(mapper: PhotosDomainMapperImpl): PhotosDomainMapper
}