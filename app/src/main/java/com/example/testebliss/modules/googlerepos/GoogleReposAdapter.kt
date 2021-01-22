package com.example.testebliss.modules.googlerepos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testebliss.R
import com.example.testebliss.models.Emoji
import com.example.testebliss.models.GoogleRepos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_emoji.view.*
import kotlinx.android.synthetic.main.item_google_repos.view.*


class GoogleReposAdapter(private val googleRepos: MutableList<GoogleRepos>) :
    RecyclerView.Adapter<GoogleReposAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_google_repos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = googleRepos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val googleRepos = googleRepos[position]

        with(holder.itemView){
            txtNameRepos.text = googleRepos.fullName
        }


    }

    fun addData(googleRepos: List<GoogleRepos>) {
        this.googleRepos.addAll(googleRepos)
        notifyDataSetChanged()
    }

    fun clearData(){
        this.googleRepos.clear()
        notifyDataSetChanged()
    }
}