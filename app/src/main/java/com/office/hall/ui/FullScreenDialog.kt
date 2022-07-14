package com.office.hall.ui

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.office.hall.R
import kotlinx.android.synthetic.main.dialog_pic.*

/**
 * Author: wpt
 * Time: 2022/7/14
 * @Desc：
 */
class FullScreenDialog(
    context: Context?,private val picPath: String?) : Dialog(
    context!!
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_pic, null)
        setContentView(view)
        window!!.setBackgroundDrawable(ColorDrawable(0x00000000))
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        Glide.with(context)
            .load(picPath)
            .into(ivPic)
        ivClose.setOnClickListener { dismiss() }
    }
}