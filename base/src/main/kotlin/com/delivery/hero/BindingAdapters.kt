package com.delivery.hero

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@BindingAdapter("bindableAdapter", "data")
fun <D : Any, A : ListAdapter<D, *>> RecyclerView.setRecycleViewProperties(
    bindableAdapter: A,
    items: List<D>?
) {

    if (adapter != bindableAdapter) adapter = bindableAdapter
    items?.let {
        bindableAdapter.submitList(it)
    }
}

@BindingAdapter("swipeToRefreshListener")
fun SwipeRefreshLayout.setSwipeToRefreshLayout(callback: SwipeRefreshLayout.OnRefreshListener) {
    setOnRefreshListener(callback)
    setColorSchemeResources(R.color.colorGreen)
}

@BindingAdapter("url")
fun ImageView.loadFromUrl(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_loading)
        .into(this)
}

@BindingAdapter("dominantColorUrl")
fun ConstraintLayout.setColorFromDominantColor(url: String) {
    val constraintLayout = this
    Glide.with(context)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                Palette.from(resource).generate { palette ->
                    constraintLayout.setBackgroundColor(
                        palette?.getMutedColor(Color.WHITE) ?: Color.WHITE
                    )
                }
            }

            override fun onLoadCleared(placeholder: Drawable?) = Unit
        })
}
