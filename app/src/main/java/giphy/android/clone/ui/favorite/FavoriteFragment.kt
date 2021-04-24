package giphy.android.clone.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import giphy.android.clone.R
import giphy.android.clone.base.view.BaseFragment
import giphy.android.clone.database.gif.LocalGif
import giphy.android.clone.databinding.FavoriteFragmentBinding

class FavoriteFragment : BaseFragment<FavoritePresenter>(R.layout.favorite_fragment), FavoriteView {

    private var _binding : FavoriteFragmentBinding? = null
    private val binding get() = _binding!!

    private val favoriteAdapter: FavoriteAdapter by lazy { FavoriteAdapter(::handleOnClickLike) }

    override fun initPresenter() {
        presenter = FavoritePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcvGifs.adapter = favoriteAdapter
    }

    override fun onResume() {
        super.onResume()

        loadValue()
    }

    private fun loadValue() {
        presenter.loadGifs()
    }

    override fun onLoadLocalGifs(localGifs: List<LocalGif>) {
        favoriteAdapter.addItems(localGifs)
    }

    override fun onSuccessDeleteLocalGif(localGif: LocalGif) {
        favoriteAdapter.remove(localGif)
        Toast.makeText(
            requireContext(),
            getString(R.string.success_delete_favorite),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun handleOnClickLike(localGif: LocalGif) {
        presenter.deleteByOriginalId(localGif)
    }
}