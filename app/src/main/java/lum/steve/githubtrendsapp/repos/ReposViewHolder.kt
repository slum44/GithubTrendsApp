package lum.steve.githubtrendsapp.repos

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import lum.steve.githubtrendsapp.R

internal class ReposViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    val textViewBio: TextView
    val textViewName: TextView
    val imageViewAvatar: ImageView

    init {
        imageViewAvatar = v.findViewById<View>(R.id.imageview_avatar) as ImageView
        textViewName = v.findViewById<View>(R.id.textview_username) as TextView
        textViewBio = v.findViewById<View>(R.id.textview_desc) as TextView
    }


}
