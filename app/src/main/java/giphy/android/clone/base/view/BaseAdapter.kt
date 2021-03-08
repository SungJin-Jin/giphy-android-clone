package giphy.android.clone.base.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

abstract class BaseAdapter<VH : BaseViewHolder<T>, T> : RecyclerView.Adapter<VH>() {

    protected val items = ArrayList<T>()

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    fun findAll() = items

    fun findByIndex(index: Int) = items[index]

    fun size() = this.items.size

    fun addItems(items: List<T>, isClear: Boolean = true) {
        if (isClear) this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    open fun addItem(item: T) {
        this.items.add(item)
        notifyItemInserted(this.items.size - 1)
    }

    fun addItemToTop(item: T) {
        this.items.add(item)
        notifyItemInserted(0)
    }

    fun update(item: T) {
        val index = this.items.indexOf(item)
        this.items[index] = item
        notifyItemChanged(index - 1)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun remove(item: T) {
        val position = items.indexOf(item)
        if (position > -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }
}