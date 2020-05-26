package org.techm.optus.ui.adapter.album

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.techm.optus.R
import org.techm.optus.data.model.album.AlbumModel

/**
 * @class{AlbumAdapter} -> display album items in recyclerview
 */
class AlbumAdapter(private var albumList: List<AlbumModel> , private var listener: OnImageClickListener , private var context: Context) : RecyclerView.Adapter<AlbumViewHolder>() {

    interface OnImageClickListener {
        fun onItemClick(item: AlbumModel?)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_album,
            parent,
            false
        ))

    /**
     * returns size of album list
     */
    override fun getItemCount() = albumList.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.itemAlbumBinding.album = albumList[position]
        holder.itemAlbumBinding.cardViewAlbumContainer.animation = AnimationUtils.loadAnimation(
            context,
            R.anim.fade_scale_animation
        )
        holder.itemAlbumBinding.imageViewAlbumPhoto.setOnClickListener {
            listener.onItemClick(albumList[position])
        }
    }
}