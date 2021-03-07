package giphy.android.clone.ui.gif

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Gif(
        val id: String = "",
        val title: String = "",
        val type: String = "",
        val slug: String = "",
        val url: String = "",
        val images: Images = Images()

) : Parcelable {
        
    fun getImage() = images.fixedWidth
}

@Parcelize
data class Images(
        @SerializedName("fixed_width") val fixedWidth: Image = Image()
) : Parcelable

@Parcelize
data class Image(
        val height: String = "",
        val width: String = "",
        val size: String = "",
        val url: String = ""
) : Parcelable