package `in`.evilcorp.demobooth.ui.imageview.di

import `in`.evilcorp.demobooth.di.AppComponent
import `in`.evilcorp.demobooth.ui.imageview.PhotoViewActivity
import dagger.Component
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PhotoViewActivityScope

@PhotoViewActivityScope
@Component(
    dependencies = [
        AppComponent::class
    ]
)
interface PhotoViewComponent {

    fun inject(activity: PhotoViewActivity)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): PhotoViewComponent
    }
}