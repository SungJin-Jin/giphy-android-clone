package giphy.android.clone.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedList
import giphy.android.clone.R
import giphy.android.clone.base.view.BaseFragment
import giphy.android.clone.databinding.TrendingFragmentBinding
import giphy.android.clone.extensions.adaptStaggeredGridLayout
import giphy.android.clone.ui.gif.Gif

class TrendingFragment : BaseFragment<TrendingPresenter>(R.layout.trending_fragment), TrendingView {

    private var _binding: TrendingFragmentBinding? = null
    private val binding get() = _binding!!

    private val gifPageAdapter: GifPageAdapter by lazy { GifPageAdapter(::handleOnClickLike) }

    override fun initPresenter() {
        presenter = TrendingPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TrendingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcvGifs.run {
            adapter = gifPageAdapter
            adaptStaggeredGridLayout()
        }

        presenter.loadGifs()
    }

    override fun onLoadPagedGifs(pagedList: PagedList<Gif>) {
        gifPageAdapter.submitList(pagedList)
    }

    override fun onSuccessSaveFavorite() {
        Toast.makeText(
            requireContext(),
            getString(R.string.success_save_favorite),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onSuccessDeleteFavorite() {
        Toast.makeText(
            requireContext(),
            getString(R.string.success_delete_favorite),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun handleOnClickLike(gif: Gif, position: Int) {
        presenter.clickLikeGif(gif)
        gifPageAdapter.notifyItemChanged(position)
    }
}