package `in`.evilcorp.demobooth.commonshared

import android.view.View
import androidx.appcompat.widget.SearchView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.untilClear(compositeDisposable: CompositeDisposable): Disposable {
    compositeDisposable.add(this)
    return this
}

data class Data<T>(
    val content: T? = null,
    val isLoading: Boolean = true,
    val error: Throwable? = null
)

fun <T> Data<T>.isStateError(): Boolean = error != null
fun <T> Data<T>.isStateLoading(): Boolean = isLoading && content == null && error == null
fun <T> Data<T>.isContentNotEmpty(): Boolean = content != null

fun View.clicks(): Observable<Unit> = Observable.create { emitter ->
    setOnClickListener { emitter.onNext(Unit) }
}

fun SearchView.queries(): Observable<String> = Observable.create { emitter ->
    setOnQueryTextListener(
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                emitter.onNext(newText.orEmpty())
                return true
            }
        }
    )
}