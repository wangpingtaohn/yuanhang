package com.weilin.screenshot.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.weilin.screenshot.R
import com.weilin.screenshot.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_base_title.view.*


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
        ivAdd1.setOnClickListener { checkPermissions() }
        tvMerge.setOnClickListener { showPreviewDialog() }
    }

    private fun checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE)
        } else {
            openAlbum()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.isNotEmpty()&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
            openAlbum()
        }
    }

    private fun openAlbum(){

        PictureSelector.create(this)
            .openSystemGallery(SelectMimeType.ofImage())
            .setSelectionMode(SelectModeConfig.MULTIPLE)
            .forSystemResult(object : OnResultCallbackListener<LocalMedia?> {
                override fun onResult(result: ArrayList<LocalMedia?>?) {
                    if (result == null || result.isEmpty()){
                        return
                    }
                    val localMedia1 = result[0]
                    pic1 = localMedia1?.path
                    showPic(ivAdd1,pic1)
                    if (result.size > 1){
                        val localMedia2 = result[1]
                        pic2 = localMedia2?.path
                        showPic(ivAdd2,localMedia2?.path)
                    }
                }
                override fun onCancel() {}
            })
    }

    private fun showPic(iv: ImageView,path: String?){
        if (TextUtils.isEmpty(path)){
            return
        }
        Glide.with(this).load(path).into(iv)
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