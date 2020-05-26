package org.techm.optus.ui.adapter.user

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.techm.optus.R
import org.techm.optus.data.model.user.UserModel

/**
 * @class{UserAdapter} -> display user items in recyclerview
 */
class UserAdapter(private var userList: List<UserModel> , private var listener: OnItemClickListener , private var context: Context): RecyclerView.Adapter<UserViewHolder>() {

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

    /**
     * returns size of user list
     */
    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemUserBinding.user = userList[position]
        holder.itemUserBinding.cardViewUserContainer.animation = AnimationUtils.loadAnimation(
            context,
            R.anim.fade_scale_animation
        )
        holder.itemUserBinding.root.setOnClickListener {
            listener.onItemClick(holder.itemUserBinding.user)
        }
    }
}