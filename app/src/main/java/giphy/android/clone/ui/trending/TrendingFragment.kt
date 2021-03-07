package giphy.android.clone.ui.trending

import android.os.Bundle
import android.view.View
import giphy.android.clone.R
import giphy.android.clone.base.view.BaseFragment

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
}