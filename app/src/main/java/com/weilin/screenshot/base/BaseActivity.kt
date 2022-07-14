package com.weilin.screenshot.base

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

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
}