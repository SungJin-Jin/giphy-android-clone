package giphy.android.clone.ui.trending

import giphy.android.clone.R
import giphy.android.clone.base.view.BaseFragment

class TrendingFragment : BaseFragment<TrendingPresenter>(R.layout.fragment_trending), TrendingView {

    override fun initPresenter() {
        presenter = TrendingPresenter(this)
    }
}