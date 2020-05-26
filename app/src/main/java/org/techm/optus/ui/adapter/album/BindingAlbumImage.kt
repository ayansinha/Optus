package org.techm.optus.ui.adapter.album

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("thumbnail")
fun loadAlbumImage(imageView: ImageView , url: String) {
    Picasso.get().load(url).into(imageView)
}