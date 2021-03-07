package giphy.android.clone.base.http

import com.google.gson.annotations.SerializedName

data class Meta(
    val status: Int = 0,
    @SerializedName("msg") val message: String = "",
    @SerializedName("response_id") val responseId: String = ""
)