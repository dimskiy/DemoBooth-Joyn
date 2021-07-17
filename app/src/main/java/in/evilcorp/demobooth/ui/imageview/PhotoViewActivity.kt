package `in`.evilcorp.demobooth.ui.imageview

import `in`.evilcorp.demobooth.R
import `in`.evilcorp.demobooth.databinding.ActivityPhotoViewBinding
import `in`.evilcorp.demobooth.platform.getAppComponent
import `in`.evilcorp.demobooth.ui.ImageDownloader
import `in`.evilcorp.demobooth.ui.imageview.di.DaggerPhotoViewComponent
import `in`.evilcorp.demobooth.ui.imageview.di.PhotoViewComponent
import `in`.evilcorp.demobooth.ui.model.PhotoItemUiModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class PhotoViewActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityPhotoViewBinding

    @Inject
    lateinit var imageDownloader: ImageDownloader

    private lateinit var component: PhotoViewComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        component = DaggerPhotoViewComponent.factory()
            .create(application.getAppComponent())
        component.inject(this)

        super.onCreate(savedInstanceState)

        viewBinding = ActivityPhotoViewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.buttonClose.setOnClickListener {
            finish()
        }

        intent.getParcelableExtra<PhotoItemUiModel>(BUNDLE_KEY_PHOTO)
            ?.let(this::showPhoto)
            ?: notifyWrongImage()
    }

    private fun notifyWrongImage() {
        AlertDialog.Builder(this)
            .setTitle(R.string.error_image_not_found_title)
            .setMessage(R.string.error_image_not_found_description)
            .show()
    }

    private fun showPhoto(photo: PhotoItemUiModel) {
        imageDownloader.downloadImage(photo.fullImageUrl, viewBinding.imageViewer)
        viewBinding.textUsername.text = photo.userName
        viewBinding.textTags.text = photo.tags.joinToString(TAGS_SEPARATOR)
    }

    companion object {
        private const val BUNDLE_KEY_PHOTO = "BUNDLE_PHOTO"
        private const val TAGS_SEPARATOR = ", "

        fun show(context: Context, photo: PhotoItemUiModel) {
            val intent = Intent(context, PhotoViewActivity::class.java).apply {
                putExtra(BUNDLE_KEY_PHOTO, photo)
            }
            context.startActivity(intent)
        }
    }
}