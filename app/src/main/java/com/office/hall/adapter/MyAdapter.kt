package com.office.hall.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.office.hall.R
import kotlinx.android.synthetic.main.item_home.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 *    author : wpt
 *    date   : 2019-10-08 14:34
 *    desc   :
 */
class MyAdapter(private var context: Activity): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false)
        return object : MyViewHolder(view) {}
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.itemView){
            var timeStr = stampToDate(System.currentTimeMillis())
            var totalTime: String? = null
            when (position) {
                0 -> {
                    timeStr = stampToDate(System.currentTimeMillis() - 2 * 60 * 60 * 1000)
                    totalTime = "1小时20分"
                }
                1 -> {
                    timeStr = stampToDate(System.currentTimeMillis() - 5 * 60 * 60 * 1000)
                    tvTitle.text = "学生出校申请"
                    totalTime = "1小时05分"
                }
                2 -> {
                    tvTitle.text = "财务用印申请"
                    totalTime = "2小时10分"
                }
            }
            tvSubTime.text = String.format("提交于： $timeStr")
            tvTotalTime.text = totalTime
            tvForm.setOnClickListener {
                itemClickListener?.onItemClick(position)
            }
        }
    }

    /*
     * 将时间戳转换为时间
     */
    fun stampToDate(s: Long): String? {
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val lt: Long = s
        val date = Date(lt)
        res = simpleDateFormat.format(date)
        return res
    }

    open class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

}