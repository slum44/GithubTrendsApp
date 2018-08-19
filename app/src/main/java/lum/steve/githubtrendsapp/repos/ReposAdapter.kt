package lum.steve.githubtrendsapp.repos

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import lum.steve.githubtrendsapp.R
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubSearchResults
import lum.steve.githubtrendsapp.repos.data.remote.model.Item
import lum.steve.githubtrendsapp.repos.trend.TrendingReposActivity


internal class ReposAdapter(private var searchResults: GitHubSearchResults?, private val context: Context,
                            private val onItemClickListener: TrendingReposActivity.OnItemClickListener) : RecyclerView.Adapter<ReposViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_repo, parent, false)
        return ReposViewHolder(v)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        val item : Item = searchResults!!.items!![position]

        holder.textViewBio.text = item.description
        holder.textViewName.text = item.fullName
        Picasso.with(context).load(item.owner!!.avatarUrl).into(holder.imageViewAvatar)

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return if (searchResults == null) {
            0
        } else searchResults!!.items!!.size
    }

    fun setItems(reposList: GitHubSearchResults) {
        this.searchResults = reposList
        notifyDataSetChanged()
    }
}