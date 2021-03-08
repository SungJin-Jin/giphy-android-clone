package giphy.android.clone.ui.favorite

import android.view.View.inflate
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import giphy.android.clone.R
import giphy.android.clone.base.view.BaseAdapter
import giphy.android.clone.base.view.BaseViewHolder
import giphy.android.clone.common.StaggeredItemDecorator
import giphy.android.clone.database.gif.LocalGif
import giphy.android.clone.extensions.drawTintColor
import giphy.android.clone.extensions.load
import kotlinx.android.synthetic.main.viewholder_trending.view.*

class FavoriteTrendingAdapter(
        private val actionOnClickLike: (LocalGif) -> Unit = {}
) : BaseAdapter<FavoriteTrendingAdapter.ViewHolder, LocalGif>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    inner class ViewHolder(parent: ViewGroup)
        : BaseViewHolder<LocalGif>(inflate(parent.context, R.layout.viewholder_favorite, null)) {
        override fun bind(gif: LocalGif) {
            with(itemView) {
                image.load(gif.url)
                image.updateLayoutParams {
                    height = StaggeredItemDecorator.getHeight(gif.height.toInt())
                }
                like.setOnClickListener {
                    like.drawTintColor(R.color.red)
                    actionOnClickLike.invoke(gif)
                }
            }
        }
    }
}