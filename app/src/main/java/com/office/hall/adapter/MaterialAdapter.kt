package com.office.hall.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luck.picture.lib.entity.LocalMedia
import com.office.hall.R
import kotlinx.android.synthetic.main.item_material.view.*

/**
 *    author : wpt
 *    date   : 2019-10-08 14:34
 *    desc   :
 */
class MaterialAdapter(private var context: Context,private var list: MutableList<LocalMedia?>): RecyclerView.Adapter<MaterialAdapter.MyViewHolder>() {

    var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_material, parent, false)
        return object : MyViewHolder(view) {}
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val path = list[position]?.path
        with(holder.itemView){
            Glide.with(context)
                .load(path)
                .into(ivItemMaterial)
            ivItemDel.setOnClickListener { itemClickListener?.onItemClick(position) }
        }
    }

    open class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

}