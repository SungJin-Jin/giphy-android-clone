package giphy.android.clone.ui.favorite

import giphy.android.clone.database.gif.LocalGif

interface FavoriteView {
    fun onLoadLocalGifs(localGifs: List<LocalGif>)
    fun onSuccessDeleteLocalGif(localGif: LocalGif)

}
