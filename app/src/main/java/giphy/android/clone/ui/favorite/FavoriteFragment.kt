package giphy.android.clone.ui.favorite

import android.widget.Toast
import giphy.android.clone.R
import giphy.android.clone.base.view.BaseFragment
import giphy.android.clone.database.gif.LocalGif
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment<FavoritePresenter>(R.layout.fragment_favorite), FavoriteView {

    private val adapter: FavoriteAdapter by lazy { FavoriteAdapter(::handleOnClickLike) }

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
        rcvGifs.adapter = adapter.apply { addItems(localGifs) }
    }

    override fun onSuccessDeleteLocalGif(localGif: LocalGif) {
        adapter.remove(localGif)
        Toast.makeText(requireContext(), getString(R.string.success_delete_favorite), Toast.LENGTH_SHORT).show()
    }

    private fun handleOnClickLike(localGif: LocalGif) {
        presenter.deleteByOriginalId(localGif)
    }
}