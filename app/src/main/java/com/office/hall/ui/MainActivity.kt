package com.office.hall.ui

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.office.hall.adapter.MyAdapter
import com.office.hall.R
import com.office.hall.base.BaseActivity
import com.office.hall.utils.SpUtils
import com.wildma.pictureselector.PictureBean
import com.wildma.pictureselector.PictureSelector
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
                    val intent = Intent(this@MainActivity, MySignActivity::class.java)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                val pictureBean =
                    data.getParcelableExtra<PictureBean>(PictureSelector.PICTURE_RESULT)
                val picPath = if (pictureBean!!.isCut) pictureBean.path else pictureBean.uri.toString()
                SpUtils.putString(this,PREVIEW_PIC_PATH,picPath)
            }
        }
    }
}