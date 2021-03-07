package giphy.android.clone.base.http

import com.google.gson.annotations.SerializedName

data class Page<T>(
    var data: List<T> = emptyList(),
    var meta: Meta = Meta(),
    var pagination: Pagination = Pagination()
)

data class Pagination(
    var offset: Int = 0,
    @SerializedName("total_count") var totalCount: Int = 0,
    var count: Int = 0
)
