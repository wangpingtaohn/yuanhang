package com.office.hall.base

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.wildma.pictureselector.PictureSelectUtils
import com.wildma.pictureselector.PictureSelector

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
        //读写权限
        val PERMISSIONS_STORAGE = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    open fun checkPermissions(){
        checkPermissions(PictureSelector.SELECT_REQUEST_CODE)
    }

    open fun checkPermissions(requestCode: Int){
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE)
        } else {
            openAlbum(requestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE){
            if(grantResults.isNotEmpty()&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openAlbum(PictureSelector.SELECT_REQUEST_CODE)
            }
        }
    }

    //启动相册的方法
    private fun openAlbum(requestCode: Int) {
        PictureSelector
            .create(this, requestCode)
            .selectPicture(false)
    }
}