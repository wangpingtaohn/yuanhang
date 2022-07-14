package com.weilin.screenshot.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.weilin.screenshot.R
import com.weilin.screenshot.base.BaseActivity
import com.wildma.pictureselector.PictureBean
import com.wildma.pictureselector.PictureSelector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.topBar
import kotlinx.android.synthetic.main.layout_base_title.view.*
import kotlinx.android.synthetic.main.layout_base_title.view.tvTitle


class MainActivity : BaseActivity() {

    private var pic1: String?= null
    private var pic2: String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTopBar()
        initView()
    }

    private fun initTopBar() {
        topBar.tvTitle.text = "伟林截图"
        topBar.ivMore.visibility = View.GONE
        topBar.ivBack.setOnClickListener { finish() }
    }

    private fun initView(){
        ivAdd1.setOnClickListener { checkPermissions(REQUEST_HEALTHY_CODE) }
        ivAdd2.setOnClickListener { checkPermissions(REQUEST_TRACK_CODE) }
        tvMerge.setOnClickListener { showPreviewDialog() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            val pictureBean =
                data.getParcelableExtra<PictureBean>(PictureSelector.PICTURE_RESULT)
            val picPath = if (pictureBean!!.isCut) pictureBean.path else pictureBean.uri.toString()
            if (requestCode == REQUEST_HEALTHY_CODE) {
                Glide.with(this).load(picPath).into(ivAdd1)
                pic1 = picPath
            } else if (requestCode == REQUEST_TRACK_CODE) {
                Glide.with(this).load(picPath).into(ivAdd2)
                pic2 = picPath
            }
        }
    }

    private fun showPreviewDialog(){
        if (TextUtils.isEmpty(pic1) || TextUtils.isEmpty(pic2)){
            Snackbar.make(rootView,"请添加图片",Snackbar.LENGTH_SHORT).show()
            return
        }
        val name: String= etName.text.toString()
        if (TextUtils.isEmpty(name)){
            Snackbar.make(rootView,"请输入姓名",Snackbar.LENGTH_SHORT).show()
            return
        }
        val preViewDialog = FullScreenDialog(this,name,pic1,pic2)
        preViewDialog.setCanceledOnTouchOutside(true)
        preViewDialog.show()
    }
}