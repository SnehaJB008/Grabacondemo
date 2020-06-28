package com.demo.grabacondemo.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.grabacondemo.R
import com.demo.grabacondemo.modules.Photo
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoListAdapter(val context: Context, val photoList: ArrayList<Photo>) :
    RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: PhotoListAdapter.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(position: Int) {
            val photoItem = photoList.get(position)
            itemView.tvTitle.text = photoItem.title

            try {
                Glide.with(context as Activity)
                    .asBitmap()
                    .load(photoItem.url)
                    .placeholder(R.drawable.posterimg)
                    .into(itemView.ivPhoto)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            itemView.setOnClickListener {
                val bundle = bundleOf("id" to photoItem.id)
                itemView.findNavController().navigate(R.id.action_dataListFragment_to_itemDetailFragment,bundle)
            }
        }
    }
}