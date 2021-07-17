package `in`.evilcorp.demobooth

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before

open class BaseRxTest {

    @Before
    fun prepare() {
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun calmDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }
}