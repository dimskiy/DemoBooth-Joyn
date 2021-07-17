package `in`.evilcorp.demobooth.ui

import android.widget.ImageView

interface ImageDownloader {

    fun downloadImage(sourceUrl: String?, targetView: ImageView)

}