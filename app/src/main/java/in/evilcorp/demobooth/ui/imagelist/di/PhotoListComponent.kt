package `in`.evilcorp.demobooth.ui.imagelist.di

import `in`.evilcorp.demobooth.di.AppComponent
import `in`.evilcorp.demobooth.ui.imagelist.PhotoListActivity
import dagger.Component
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PhotoListActivityScope

@PhotoListActivityScope
@Component(
    modules = [
        PhotoListModule::class
    ],
    dependencies = [
        AppComponent::class
    ]
)
interface PhotoListComponent {

    fun inject(activity: PhotoListActivity)

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): PhotoListComponent
    }
}