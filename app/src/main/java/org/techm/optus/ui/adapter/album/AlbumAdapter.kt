package org.techm.optus.ui.adapter.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.techm.optus.R
import org.techm.optus.data.model.album.AlbumModel

class AlbumAdapter(private var albumList: List<AlbumModel> , private var listener: OnImageClickListener) : RecyclerView.Adapter<AlbumViewHolder>() {

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

    override fun getItemCount() = albumList.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.itemAlbumBinding.album = albumList[position]
        holder.itemAlbumBinding.imageViewAlbumPhoto.setOnClickListener {
            listener.onItemClick(albumList[position])
        }
    }
}