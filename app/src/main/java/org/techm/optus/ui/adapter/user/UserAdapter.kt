package org.techm.optus.ui.adapter.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.techm.optus.R
import org.techm.optus.data.model.user.UserModel

class UserAdapter(private var userList: List<UserModel> , private var listener: OnItemClickListener): RecyclerView.Adapter<UserViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: UserModel?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        ))

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemUserBinding.user = userList[position]
        holder.itemUserBinding.root.setOnClickListener {
            listener.onItemClick(holder.itemUserBinding.user)
        }
    }
}