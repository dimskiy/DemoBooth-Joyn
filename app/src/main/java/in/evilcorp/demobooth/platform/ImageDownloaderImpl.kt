package `in`.evilcorp.demobooth.platform

import `in`.evilcorp.demobooth.ui.ImageDownloader
import android.widget.ImageView
import com.squareup.picasso.Picasso

internal class ImageDownloaderImpl(
    private val picassoInstance: Picasso
) : ImageDownloader {

    override fun downloadImage(sourceUrl: String?, targetView: ImageView) {
        picassoInstance
            .load(sourceUrl)
            .into(targetView)
    }
}