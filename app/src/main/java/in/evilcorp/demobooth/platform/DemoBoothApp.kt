package `in`.evilcorp.demobooth.platform

import `in`.evilcorp.demobooth.di.AppComponent
import `in`.evilcorp.demobooth.di.DaggerAppComponent
import android.app.Application

fun Application.getAppComponent(): AppComponent = (this as DemoBoothApp).appComponent

class DemoBoothApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent.factory()
            .create(this)

        super.onCreate()
    }
}