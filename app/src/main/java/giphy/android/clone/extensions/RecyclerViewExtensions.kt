package giphy.android.clone.extensions

import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

fun RecyclerView.adaptStaggeredGridLayout() {
    layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
    }
    setHasFixedSize(true)
    startAnimation(AlphaAnimation(0.0f, 1.0f).apply { duration = 1000 })
    itemAnimator = null
}