package giphy.android.clone.ui.gif

import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.core.view.updateLayoutParams
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import giphy.android.clone.R
import giphy.android.clone.extensions.load
import kotlinx.android.synthetic.main.viewholder_gif.view.*

class GifPageAdapter(
) : PagedListAdapter<Gif, GifPageAdapter.ViewHolder>(diffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(View.inflate(parent.context, R.layout.viewholder_gif, null)) {

        fun bind(gif: Gif?) {
            with(itemView) {
                gif?.let {
                    image.load(gif.getImage().url)
                    image.updateLayoutParams {
                        height = (gif.getImage().height.toInt() * STAGGERED_GRID_RATIO).toInt()
                    }
                }
            }
        }
    }

    companion object {
        private val STAGGERED_GRID_RATIO =
            Resources.getSystem().displayMetrics.widthPixels / 2 / 200.0
    }
}

fun diffItemCallback(): DiffUtil.ItemCallback<Gif> {
    return object : DiffUtil.ItemCallback<Gif>() {
        override fun areItemsTheSame(old: Gif, new: Gif) = old.id == new.id
        override fun areContentsTheSame(old: Gif, new: Gif) = old == new
    }
}