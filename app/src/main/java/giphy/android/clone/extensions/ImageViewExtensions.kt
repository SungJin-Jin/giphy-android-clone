package giphy.android.clone.extensions

import android.graphics.PorterDuff
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


fun ImageView.load(url: String?, options: RequestOptions = RequestOptions()) =
    Glide.with(this)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(options)
        .into(this)


fun ImageView.drawTintColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        imageTintList = ContextCompat.getColorStateList(context, color)
    } else {
        drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
}