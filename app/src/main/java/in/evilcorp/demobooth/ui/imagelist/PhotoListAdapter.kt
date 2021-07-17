package `in`.evilcorp.demobooth.ui.imagelist

import `in`.evilcorp.demobooth.R
import `in`.evilcorp.demobooth.commonshared.FilterableRecycleAdapter
import `in`.evilcorp.demobooth.databinding.LayoutListItemBinding
import `in`.evilcorp.demobooth.ui.ImageDownloader
import `in`.evilcorp.demobooth.ui.model.PhotoItemUiModel
import android.view.LayoutInflater
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class PhotoListAdapter(
    private val imageDownloader: ImageDownloader
) : FilterableRecycleAdapter<PhotoItemUiModel, PhotoListAdapter.ViewHolder>() {

    private val clicksSubject = PublishSubject.create<PhotoItemUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    fun observeClicks(): Observable<PhotoItemUiModel> = clicksSubject

    inner class ViewHolder(private val viewBinding: LayoutListItemBinding) : BindableHolder<PhotoItemUiModel>(
        viewBinding.root
    ) {
        private val emptyPlaceholderText = itemView.context.getString(R.string.empty_text_placeholder)

        override fun bind(item: PhotoItemUiModel) {
            with(viewBinding) {
                imageDownloader.downloadImage(item.thumbnailUrl, imageThumbnail)
                textUserName.text = item.userName

                textViewsNumber.text = getNumberText(item.viewsCount)
                textDownloadsNumber.text = getNumberText(item.downloadsCount)
                textLikesNumber.text = getNumberText(item.likesCount)

                root.setOnClickListener { clicksSubject.onNext(item) }
            }
        }

        private fun getNumberText(number: Int?): String =
            number.toString()
                .takeIf(String::isNotEmpty)
                ?: emptyPlaceholderText
    }
}