package `in`.evilcorp.demobooth.ui.imagelist

import `in`.evilcorp.demobooth.commonshared.RecyclerItemDecorator
import `in`.evilcorp.demobooth.commonshared.clicks
import `in`.evilcorp.demobooth.commonshared.untilClear
import `in`.evilcorp.demobooth.databinding.ActivityPhotoListBinding
import `in`.evilcorp.demobooth.platform.getAppComponent
import `in`.evilcorp.demobooth.ui.ImageDownloader
import `in`.evilcorp.demobooth.ui.UserMessageTypes
import `in`.evilcorp.demobooth.ui.imagelist.di.DaggerPhotoListComponent
import `in`.evilcorp.demobooth.ui.imagelist.di.PhotoListComponent
import `in`.evilcorp.demobooth.ui.imageview.PhotoViewActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PhotoListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var imageDownloader: ImageDownloader

    private lateinit var component: PhotoListComponent

    private val viewModel: PhotoListViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory).get(PhotoListViewModel::class.java)
    }

    private lateinit var photosAdapter: PhotoListAdapter

    private lateinit var viewBindings: ActivityPhotoListBinding
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        component = DaggerPhotoListComponent.factory()
            .create(application.getAppComponent())
        component.inject(this)

        super.onCreate(savedInstanceState)

        viewBindings = ActivityPhotoListBinding.inflate(layoutInflater)
        setContentView(viewBindings.root)

        photosAdapter = PhotoListAdapter(imageDownloader)

        with(viewBindings.listPhotos) {
            adapter = photosAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(RecyclerItemDecorator(context))
        }

        if (savedInstanceState == null) doInitialSearch()
    }

    private fun doInitialSearch() {
        val initialQuery = viewBindings.searchEditText.hint
        viewModel.onSearchQueryChange(initialQuery)
            .subscribe()
            .untilClear(disposables)
    }

    override fun onStart() {
        super.onStart()

        bindCommands()
        bindEvents()
    }

    private fun bindCommands() {
        viewModel.observePhotos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(photosAdapter::setNewItems)
            .untilClear(disposables)

        viewModel.observePhotoDetailsRequested()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { PhotoViewActivity.show(this, it) }
            .untilClear(disposables)

        viewModel.observeUserNotifications()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::showUserMessage)
            .untilClear(disposables)

        viewModel.observeShowProgress()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { viewBindings.progressView.isVisible = it }
            .untilClear(disposables)
    }

    private fun bindEvents() {
        photosAdapter.observeClicks()
            .flatMapCompletable(viewModel::onItemClick)
            .subscribe()
            .untilClear(disposables)

        viewBindings.searchButton.clicks()
            .map { viewBindings.searchEditText.text }
            .flatMapCompletable(viewModel::onSearchQueryChange)
            .subscribe()
            .untilClear(disposables)
    }

    private fun showUserMessage(messageType: UserMessageTypes) {
        Snackbar
            .make(viewBindings.layoutRoot, messageType.messageRes, Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    companion object {
        private const val QUERY_TIMEOUT_MS = 200L
    }
}