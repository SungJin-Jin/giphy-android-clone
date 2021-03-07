package giphy.android.clone.ui.favorite

import giphy.android.clone.R
import giphy.android.clone.base.view.BaseFragment

class FavoriteFragment : BaseFragment<FavoritePresenter>(R.layout.fragment_favorite), FavoriteView {

    override fun initPresenter() {
        presenter = FavoritePresenter(this)
    }

}