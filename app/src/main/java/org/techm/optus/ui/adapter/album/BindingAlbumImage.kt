package org.techm.optus.ui.adapter.album

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("thumbnail")
fun loadAlbumImage(imageView: ImageView , url: String) {

    val image = BitmapFactory.decodeStream(url.byteInputStream())
    imageView.setImageBitmap(image)
}