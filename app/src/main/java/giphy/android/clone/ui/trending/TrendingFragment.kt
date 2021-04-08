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

    private val gifPageAdapter: GifPageAdapter by lazy { GifPageAdapter(::handleOnClickLike) }

    override fun initPresenter() {
        presenter = TrendingPresenter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        loadValue()
    }

    private fun initView() {
        with(rcvGifs) {
            adapter = gifPageAdapter
            adaptStaggeredGridLayout()
        }
    }

    private fun loadValue() {
        presenter.loadGifs()
    }

    override fun onLoadPagedGifs(pagedList: PagedList<Gif>) {
        gifPageAdapter.submitList(pagedList)
    }

    override fun onSuccessSaveFavorite() {
        Toast.makeText(requireContext(), getString(R.string.success_save_favorite), Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDeleteFavorite() {
        Toast.makeText(requireContext(), getString(R.string.success_delete_favorite), Toast.LENGTH_SHORT).show()
    }

    private fun handleOnClickLike(gif: Gif, position: Int) {
        presenter.clickLikeGif(gif)
        gifPageAdapter.notifyItemChanged(position)
    }
}