package giphy.android.clone.ui.trending

import androidx.paging.PagedList
import giphy.android.clone.ui.gif.Gif

interface TrendingView {
    fun onLoadPagedGifs(pagedList: PagedList<Gif>)
    fun onSuccessSaveFavorite()
    fun onSuccessDeleteFavorite()

}
