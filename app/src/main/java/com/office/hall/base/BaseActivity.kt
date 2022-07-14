package com.office.hall.base

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.office.hall.utils.SpUtils

/**
 * Author: wpt
 * Time: 2022/7/12
 * @Desc：
 */
open class BaseActivity : AppCompatActivity()  {

    companion object {
        //请求权限状态码
        const val REQUEST_PERMISSION_CODE = 0x0011
        //请求健康码
        const val REQUEST_HEALTHY_CODE = 0x0022
        //请求行程码
        const val REQUEST_TRACK_CODE = 0x0033
        //请求材料
        const val REQUEST_MATERIAL_CODE = 0x0044
        //预览图片路径
        const val PREVIEW_PIC_PATH = "preViewPicPath"
        //读写权限
        val PERMISSIONS_STORAGE = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    /*open fun checkPermissions(callBack:(result: ArrayList<LocalMedia?>?) -> Unit){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE)
        } else {
            openAlbum(callBack)
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

    open fun openAlbum(callBack:(result: ArrayList<LocalMedia?>?) -> Unit){
        PictureSelector.create(this)
            .openSystemGallery(SelectMimeType.ofImage())
            .setSelectionMode(SelectModeConfig.SINGLE)
            .forSystemResult(object : OnResultCallbackListener<LocalMedia?> {
                override fun onResult(result: ArrayList<LocalMedia?>?) {
                    callBack(result)
                }
                override fun onCancel() {}
            })
    }*/
}