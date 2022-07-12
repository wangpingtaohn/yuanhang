package com.office.hall

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_home.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 *    author : wpt
 *    date   : 2019-10-08 14:34
 *    desc   :
 */
class MyAdapter(private var context: Activity): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false)
        return object : MyViewHolder(view) {}
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val str: String = sdf.format(Date())
        with(holder.itemView){
            tvSubTime.text = String.format("提交于： $str")
            if (position == 2){
                tvTitle.text = "财务用印申请"
            }
        }
        holder.itemView.setOnClickListener {
            if (position == 2){
                return@setOnClickListener
            }
            context.startActivity(Intent(context,MySignActivity::class.java))
        }
    }

    open class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}