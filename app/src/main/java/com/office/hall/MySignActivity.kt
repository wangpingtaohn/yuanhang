package com.office.hall

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.topBar
import kotlinx.android.synthetic.main.activity_sign.*
import kotlinx.android.synthetic.main.layout_base_title.view.*

class MySignActivity : BaseActivity() {

    private var picPath: String? = null

    private var preViewDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        initTopBar()
        initData()
        initView()
    }

    private fun initTopBar(){
        topBar.tvTitle.text = "我的签批"
        topBar.ivBack.setOnClickListener { finish() }
    }

    private fun initData(){
        picPath = intent.getStringExtra("pic")
    }

    private fun initView(){
        tvPreview.setOnClickListener { showPreviewDialog() }
    }

    private fun showPreviewDialog(){
        if (TextUtils.isEmpty(picPath)){
            Snackbar.make(signRootView,"没有找相关的报表可预览",Snackbar.LENGTH_SHORT).show()
            return
        }
        if (preViewDialog == null){
            preViewDialog = Dialog(this)
            preViewDialog?.setContentView(R.layout.dialog_pic)
            preViewDialog?.setCanceledOnTouchOutside(true)
            val iv = preViewDialog!!.findViewById<ImageView>(R.id.ivPic)
            //使用 Glide 加载图片
            Glide.with(this)
                .load(picPath)
                .apply(RequestOptions.centerCropTransform()).into(iv)
            iv.setOnClickListener { preViewDialog?.dismiss() }
        }

        preViewDialog?.show()
    }

}