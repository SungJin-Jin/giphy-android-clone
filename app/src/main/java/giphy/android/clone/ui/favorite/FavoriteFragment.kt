package giphy.android.clone.ui.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import giphy.android.clone.R
import giphy.android.clone.base.view.BaseFragment
import giphy.android.clone.database.gif.LocalGif
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment<FavoritePresenter>(R.layout.fragment_favorite), FavoriteView {

    private val favoriteAdapter: FavoriteAdapter by lazy { FavoriteAdapter(::handleOnClickLike) }

    override fun initPresenter() {
        presenter = FavoritePresenter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun onResume() {
        super.onResume()

        loadValue()
    }

    private fun initView() {
        rcvGifs.adapter = favoriteAdapter
    }

    private fun loadValue() {
        presenter.loadGifs()
    }

    override fun onLoadLocalGifs(localGifs: List<LocalGif>) {
        favoriteAdapter.addItems(localGifs)
    }

    override fun onSuccessDeleteLocalGif(localGif: LocalGif) {
        favoriteAdapter.remove(localGif)
        Toast.makeText(requireContext(), getString(R.string.success_delete_favorite), Toast.LENGTH_SHORT).show()
    }

    private fun handleOnClickLike(localGif: LocalGif) {
        presenter.deleteByOriginalId(localGif)
    }
}