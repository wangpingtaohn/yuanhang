package com.office.hall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_base_title.view.*

class MySignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        initTopBar()
    }

    private fun initTopBar(){
        topBar.tvTitle.text = "我的签批"
        topBar.ivBack.setOnClickListener { finish() }
    }

}