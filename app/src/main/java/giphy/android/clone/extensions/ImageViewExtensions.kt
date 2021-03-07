package giphy.android.clone.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


fun ImageView.load(url: String?, options: RequestOptions = RequestOptions()) =
    Glide.with(this)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(options)
        .into(this)

