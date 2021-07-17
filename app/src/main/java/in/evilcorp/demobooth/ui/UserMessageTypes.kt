package `in`.evilcorp.demobooth.ui

import `in`.evilcorp.demobooth.R
import androidx.annotation.StringRes

enum class UserMessageTypes(@StringRes val messageRes: Int) {
    QUERY_TOO_SHORT(R.string.message_query_too_short),
    URL_NOT_FOUND(R.string.error_image_not_found_title),
    ERROR_FETCHING_IMAGES(R.string.message_can_not_load_result)
}