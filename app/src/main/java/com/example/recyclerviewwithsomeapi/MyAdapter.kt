package com.example.recyclerviewwithsomeapi

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context: Activity, val productArrayList: List<Product>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var myListener: onItemClickLister

    interface onItemClickLister {
        fun onItemClicking(position: Int)
    }

    fun setOnItemClickListener(lister: onItemClickLister) {
        myListener = lister
    }

    class MyViewHolder(itemView: View, lister: onItemClickLister) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var image: ShapeableImageView

        // to initialised above item
        init {
            title = itemView.findViewById(R.id.productTitle)
            image = itemView.findViewById(R.id.productImg)
            itemView.setOnClickListener {
                lister.onItemClicking(adapterPosition)
            }
        }
    }

    // to create new view instance
    // when layout manager fails to find a suitable view for each item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.cusotm_item, parent, false)
        return MyViewHolder(itemView, myListener)
    }

    // how many item are there in your array or productArrayList
    override fun getItemCount(): Int {
        return productArrayList.size
    }

    // populate items with data
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position]
        holder.title.text = currentItem.title
        // image view for url
        // it need an api call picasso
        Picasso.get().load(currentItem.thumbnail).into(holder.image)

    }


}