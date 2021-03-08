package giphy.android.clone.ui.trending

import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import giphy.android.clone.R
import giphy.android.clone.common.StaggeredItemDecorator
import giphy.android.clone.extensions.drawTintColor
import giphy.android.clone.extensions.load
import giphy.android.clone.ui.gif.Gif
import kotlinx.android.synthetic.main.viewholder_trending.view.*

class GifPageAdapter(
    private val actionOnClickLike: (Gif) -> Unit = {}
) : PagedListAdapter<Gif, GifPageAdapter.ViewHolder>(diffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(View.inflate(parent.context, R.layout.viewholder_trending, null)) {

        fun bind(gif: Gif?) {
            with(itemView) {
                gif?.let {
                    image.load(gif.getImage().url)
                    image.updateLayoutParams {
                        height = StaggeredItemDecorator.getHeight(gif.getImage().height.toInt())
                    }
                    like.drawTintColor(R.color.cool_grey)

                    like.setOnClickListener {
                        gif.isClicked = !gif.isClicked
                        like.drawTintColor(getLikeColor(gif))
                        actionOnClickLike.invoke(gif)
                    }
                }
            }
        }

        private fun getLikeColor(gif: Gif) = if (gif.isClicked) {
            R.color.red
        } else {
            R.color.cool_grey
        }
    }

}

fun diffItemCallback(): DiffUtil.ItemCallback<Gif> {
    return object : DiffUtil.ItemCallback<Gif>() {
        override fun areItemsTheSame(old: Gif, new: Gif) = old.id == new.id
        override fun areContentsTheSame(old: Gif, new: Gif) = old == new
    }
}