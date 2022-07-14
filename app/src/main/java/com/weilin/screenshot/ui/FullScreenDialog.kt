package com.weilin.screenshot.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.weilin.screenshot.R
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.dialog_pic.*

/**
 * Author: wpt
 * Time: 2022/7/13
 *
 * @Desc：
 */
class FullScreenDialog(
    context: Context?,
    private val name: String?, private val pic1: String?, private val pic2: String?
) : Dialog(
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
        view.findViewById<ImageView>(R.id.ivClose).setOnClickListener { dismiss() }
        initView()
    }

    private fun initView() {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pic1) || TextUtils.isEmpty(pic2)) {
            return
        }
        tvName.text = name
        Glide.with(context).load(pic1).into(ivPic1)
        Glide.with(context).load(pic2).into(ivPic2)
    }
}