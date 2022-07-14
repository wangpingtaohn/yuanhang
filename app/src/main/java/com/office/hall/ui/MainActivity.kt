package com.office.hall.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.office.hall.adapter.MyAdapter
import com.office.hall.R
import com.office.hall.base.BaseActivity
import com.office.hall.utils.SpUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_base_title.view.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTopBar()
        initRv()
    }

    private fun initTopBar() {
        topBar.tvTitle.text = "我的发起"
        topBar.ivBack.setOnClickListener { finish() }
    }

    private fun initRv() {
        val myAdapter = MyAdapter(this@MainActivity)
        myAdapter.itemClickListener = object : MyAdapter.ItemClickListener {
            override fun onItemClick(position: Int) {
                if (position == 2) {
                    checkPermissions()
                } else {
                    val intent = Intent(
                        this@MainActivity, if (position == 1) {
                            LeaveSignActivity::class.java
                        } else BackSignActivity::class.java
                    )
                    startActivity(intent)
                }
            }

        }
        with(rvHome) {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = myAdapter
        }
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
            .setSelectionMode(SelectModeConfig.SINGLE)
            .forSystemResult(object : OnResultCallbackListener<LocalMedia?> {
                override fun onResult(result: ArrayList<LocalMedia?>?) {
                    if (result == null || result.isEmpty()){
                        return
                    }
                    val localMedia1 = result[0]
                    SpUtils.putString(this@MainActivity,PREVIEW_PIC_PATH,localMedia1?.path)
                }
                override fun onCancel() {}
            })
    }
}