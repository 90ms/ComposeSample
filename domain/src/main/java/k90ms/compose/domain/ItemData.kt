package k90ms.compose.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemData(
    val title: String,
    var isFavorite: Boolean = false
): Parcelable
