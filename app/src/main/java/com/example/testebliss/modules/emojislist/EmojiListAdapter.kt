package com.example.testebliss.modules.emojislist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testebliss.R
import com.example.testebliss.models.Emoji
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_emoji.view.*


class EmojiListAdapter(private val emojiList: MutableList<Emoji>) :
    RecyclerView.Adapter<EmojiListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_emoji, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = emojiList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val emoji = emojiList[position]

        with(holder.itemView){
            Picasso.get().load(emoji.url).into(ivEmojiList)
            setOnClickListener {
                emojiList.removeAt(position)
                notifyItemRemoved(position)
            }
        }


    }
}