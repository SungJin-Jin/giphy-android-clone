package giphy.android.clone.ui.trending

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.paging.PagedList
import giphy.android.clone.R
import giphy.android.clone.base.view.BaseFragment
import giphy.android.clone.extensions.adaptStaggeredGridLayout
import giphy.android.clone.ui.gif.Gif
import kotlinx.android.synthetic.main.fragment_trending.*

class TrendingFragment : BaseFragment<TrendingPresenter>(R.layout.fragment_trending), TrendingView {

    override fun initPresenter() {
        presenter = TrendingPresenter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadValue()
    }

    private fun loadValue() {
        presenter.loadGifs()
    }

    override fun onLoadPagedGifs(pagedList: PagedList<Gif>) {
        with(rcvGifs) {
            adapter = GifPageAdapter(::handleOnClickLike).apply { submitList(pagedList) }
            adaptStaggeredGridLayout()
        }
    }

    private fun handleOnClickLike(gif: Gif) {
        presenter.clickLikeGif(gif)
    }
}