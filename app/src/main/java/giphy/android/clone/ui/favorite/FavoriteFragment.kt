package giphy.android.clone.ui.favorite

import giphy.android.clone.R
import giphy.android.clone.base.view.BaseFragment
import giphy.android.clone.database.gif.LocalGif
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment<FavoritePresenter>(R.layout.fragment_favorite), FavoriteView {

    override fun initPresenter() {
        presenter = FavoritePresenter(this)
    }

    override fun onResume() {
        super.onResume()
        loadValue()
    }

    private fun loadValue() {
        presenter.loadGifs()
    }

    override fun onLoadLocalGifs(localGifs: List<LocalGif>) {
        rcvGifs.adapter = FavoriteTrendingAdapter(::handleOnClickLike)
                .apply { addItems(localGifs) }
    }

    private fun handleOnClickLike(localGif: LocalGif) {
        presenter.delteLocalGif(localGif)
    }
}