package com.office.hall.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.office.hall.R
import com.office.hall.base.BaseActivity
import com.office.hall.utils.SpUtils
import com.wildma.pictureselector.PictureBean
import com.wildma.pictureselector.PictureSelector
import kotlinx.android.synthetic.main.activity_main.topBar
import kotlinx.android.synthetic.main.activity_sign.*
import kotlinx.android.synthetic.main.layout_base_title.view.*

class MySignActivity : BaseActivity() {

    companion object {
        const val MATERIAL_PIC_PATH = "materialPicPath"
        const val Sign_Time = "signTime"
        const val APPLY_SIGN_TIME = "applySignTime"
        const val TEACHER_SIGN_TIME = "teacherSignTime"
        const val SECRETARY_SIGN_TIME = "secretarySignTime"
        const val LEFT_TIME = "leftTime"
        const val TRACK = "track"
    }

    private var preViewDialog: Dialog? = null

    private var mEnable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        initTopBar()
        initData()
        initView()
        initEditText()
        setEditTextEnable()
    }

    private fun initTopBar(){
        topBar.tvTitle.text = "我的签批"
        topBar.ivBack.setOnClickListener { finish() }
        topBar.ivMore.setOnClickListener {
            mEnable = !mEnable
            setEditTextEnable()
        }
    }

    private fun initData(){
        val signTime = SpUtils.getString(this, Sign_Time)
        if (!TextUtils.isEmpty(signTime)){
            etSignTime.text = Editable.Factory.getInstance().newEditable(signTime)
        }
        val applySignTime = SpUtils.getString(this, APPLY_SIGN_TIME)
        if (!TextUtils.isEmpty(applySignTime)){
            etApplySignTime.text = Editable.Factory.getInstance().newEditable(applySignTime)
        }
        val teacherSignTime = SpUtils.getString(this, TEACHER_SIGN_TIME)
        if (!TextUtils.isEmpty(teacherSignTime)){
            etTeacherSignTime.text = Editable.Factory.getInstance().newEditable(teacherSignTime)
        }
        val secretarySignTime = SpUtils.getString(this, SECRETARY_SIGN_TIME)
        if (!TextUtils.isEmpty(secretarySignTime)){
            etSecretarySignTime.text = Editable.Factory.getInstance().newEditable(secretarySignTime)
        }
        val leftTime = SpUtils.getString(this, LEFT_TIME)
        if (!TextUtils.isEmpty(leftTime)){
            etLeftTime.text = Editable.Factory.getInstance().newEditable(leftTime)
        }
        val track = SpUtils.getString(this, TRACK)
        if (!TextUtils.isEmpty(track)){
            etTrack.text = Editable.Factory.getInstance().newEditable(track)
        }
        val pic = SpUtils.getString(this, MATERIAL_PIC_PATH)
        if (!TextUtils.isEmpty(pic)){
            Glide.with(this)
                .load(pic)
                .apply(RequestOptions.centerCropTransform()).into(ivMaterial)
        }
    }

    private fun initView(){
        tvPreview.setOnClickListener { showPreviewDialog() }
        ivMaterial.setOnClickListener { openAlbum(REQUEST_MATERIAL_CODE) }
    }

    private fun initEditText(){
        etSignTime.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s == null){
                    return
                }
                SpUtils.putString(this@MySignActivity, Sign_Time,s.toString())
            }

        })
        etApplySignTime.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s == null){
                    return
                }
                SpUtils.putString(this@MySignActivity, APPLY_SIGN_TIME,s.toString())
            }

        })
        etTeacherSignTime.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s == null){
                    return
                }
                SpUtils.putString(this@MySignActivity, TEACHER_SIGN_TIME,s.toString())
            }

        })
        etSecretarySignTime.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s == null){
                    return
                }
                SpUtils.putString(this@MySignActivity, SECRETARY_SIGN_TIME,s.toString())
            }

        })
        etLeftTime.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s == null){
                    return
                }
                SpUtils.putString(this@MySignActivity, LEFT_TIME,s.toString())
            }

        })
        etTrack.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s == null){
                    return
                }
                SpUtils.putString(this@MySignActivity, TRACK,s.toString())
            }

        })
    }

    private fun setEditTextEnable(){
        etSignTime.isFocusable = mEnable
        etSignTime.isFocusableInTouchMode = mEnable

        etApplySignTime.isFocusable = mEnable
        etApplySignTime.isFocusableInTouchMode = mEnable

        etTeacherSignTime.isFocusable = mEnable
        etTeacherSignTime.isFocusableInTouchMode = mEnable

        etSecretarySignTime.isFocusable = mEnable
        etSecretarySignTime.isFocusableInTouchMode = mEnable

        etLeftTime.isFocusable = mEnable
        etLeftTime.isFocusableInTouchMode = mEnable

        etTrack.isFocusable = mEnable
        etTrack.isFocusableInTouchMode = mEnable
    }

    private fun showPreviewDialog(){
        val picPath = SpUtils.getString(this,PREVIEW_PIC_PATH)
        if (TextUtils.isEmpty(picPath)){
            Snackbar.make(signRootView,"没有相关的表单可预览",Snackbar.LENGTH_SHORT).show()
            return
        }
        if (preViewDialog == null){
            preViewDialog = FullScreenDialog(this,picPath)
            /*preViewDialog?.setContentView(R.layout.dialog_pic)
            val iv = preViewDialog!!.findViewById<ImageView>(R.id.ivPic)
            //使用 Glide 加载图片
            Glide.with(this)
                .load(picPath)
                .into(iv)
            iv.setOnClickListener { preViewDialog?.dismiss() }*/
        }

        preViewDialog?.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var pictureBean:PictureBean? = null
        if (data != null) {
            pictureBean =
                data.getParcelableExtra(PictureSelector.PICTURE_RESULT)
        }
        if (pictureBean == null){
            return
        }
        val picPath = if (pictureBean.isCut) pictureBean.path else pictureBean.uri.toString()
        if (TextUtils.isEmpty(picPath)){
            return
        }
        SpUtils.putString(this,MATERIAL_PIC_PATH,picPath)
        Glide.with(this)
            .load(picPath)
            .apply(RequestOptions.centerCropTransform()).into(ivMaterial)
    }

}