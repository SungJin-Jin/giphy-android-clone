package giphy.android.clone.common

import android.content.res.Resources

object StaggeredItemDecorator {
    private val STAGGERED_GRID_RATIO =
        Resources.getSystem().displayMetrics.widthPixels / 2 / 200.0

    fun getHeight(height: Int): Int {
        return (height * STAGGERED_GRID_RATIO).toInt()
    }
}