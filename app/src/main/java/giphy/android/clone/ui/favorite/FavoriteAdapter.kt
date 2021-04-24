package giphy.android.clone.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import giphy.android.clone.base.view.BaseAdapter
import giphy.android.clone.base.view.BaseViewHolder
import giphy.android.clone.common.StaggeredItemDecorator
import giphy.android.clone.database.gif.LocalGif
import giphy.android.clone.databinding.ViewholderFavoriteBinding
import giphy.android.clone.extensions.load

class FavoriteAdapter(
    private val actionOnClickLike: (LocalGif) -> Unit = {}
) : BaseAdapter<FavoriteAdapter.ViewHolder, LocalGif>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ViewholderFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class ViewHolder(private val binding: ViewholderFavoriteBinding) :
        BaseViewHolder<LocalGif>(binding.root) {

        override fun bind(gif: LocalGif) {
            with(binding) {
                image.load(gif.url)
                image.updateLayoutParams {
                    height = StaggeredItemDecorator.getHeight(gif.height.toInt())
                }
                like.setOnClickListener {
                    actionOnClickLike.invoke(gif)
                }
            }
        }
    }
}