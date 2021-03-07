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
        @SerializedName("bitly_url") val bitlyUrl: String = "",
        @SerializedName("embed_url") val embedUrl: String = "",
        val username: String = "",
        val source: String = "",
        val rating: String = "",
        val user: User = User(),
        @SerializedName("source_tld") val sourceTld: String = "",
        @SerializedName("source_post_url") val sourcePostUrl: String = "",
        @SerializedName("update_datetime") val updateDatetime: String = "",
        @SerializedName("create_datetime") val createDatetime: String = "",
        @SerializedName("import_datetime") val importDatetime: String = "",
        @SerializedName("trending_datetime") val trendingDatetime: String = "",
        val images: Images = Images()

) : Parcelable {
        
    fun getImage() = images.fixedWidthDownSampled
}

@Parcelize
data class User(
        @SerializedName("avatar_url") val avatarUrl: String = "",
        @SerializedName("banner_url") val bannerUrl: String = "",
        @SerializedName("profile_url") val profileUrl: String = "",
        val username: String = "",
        @SerializedName("display_name") val displayName: String = ""

) : Parcelable

@Parcelize
data class Images(
        @SerializedName("fixed_width_downsampled") val fixedWidthDownSampled: Image = Image()
) : Parcelable

@Parcelize
data class Image(
        val height: String = "",
        val width: String = "",
        val size: String = "",
        val url: String = ""
) : Parcelable