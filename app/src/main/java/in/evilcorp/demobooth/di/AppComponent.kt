package `in`.evilcorp.demobooth.di

import `in`.evilcorp.demobooth.domain.PhotoInteractor
import `in`.evilcorp.demobooth.platform.apiservice.PixabayService.Companion.API_KEY_LABEL
import `in`.evilcorp.demobooth.ui.ImageDownloader
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DomainModule::class,
        PlatformModule::class
    ]
)
interface AppComponent {

    fun providePhotoInteractor(): PhotoInteractor

    fun provideImageDownloader(): ImageDownloader

    fun provideContext(): Context

    @Named(API_KEY_LABEL)
    fun providePixabayApiKey(): String

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}