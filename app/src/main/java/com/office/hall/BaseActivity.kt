package com.office.hall

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.wildma.pictureselector.PictureSelector

/**
 * Author: wpt
 * Time: 2022/7/12
 * @Desc：
 */
open class BaseActivity : AppCompatActivity()  {

    //请求状态码
    private val REQUEST_PERMISSION_CODE = 0x0011
    //读写权限
    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )


    open fun checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,REQUEST_PERMISSION_CODE)
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
        if (requestCode == REQUEST_PERMISSION_CODE){
            if(grantResults.isNotEmpty()&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openAlbum()
            }
        }
    }

    //启动相册的方法
    private fun openAlbum() {
        PictureSelector
            .create(this, PictureSelector.SELECT_REQUEST_CODE)
            .selectPicture(false)
    }
}