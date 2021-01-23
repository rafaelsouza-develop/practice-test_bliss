package com.example.testebliss.modules.avatars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testebliss.R
import com.example.testebliss.models.RepoUserName
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_avatar.view.*

class AvatarsAdapter(
    private val avatars: List<RepoUserName>,
    private val listner: AvatarAdapterListners
) :
    RecyclerView.Adapter<AvatarsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = avatars.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val avatar = avatars[position]
        with(holder.itemView) {
            Picasso.get().load(avatar.avatarUrl).into(ivAvatar)
            ivAvatar.setOnClickListener { listner.onRemoveBD(avatar) }
        }

    }

    interface AvatarAdapterListners {

        fun onRemoveBD(avatar: RepoUserName)
    }
}