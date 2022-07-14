package com.office.hall.ui

import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.office.hall.R
import com.office.hall.adapter.MaterialAdapter
import com.office.hall.base.BaseActivity
import com.office.hall.utils.SpUtils
import kotlinx.android.synthetic.main.activity_leave_sign.*
import kotlinx.android.synthetic.main.activity_main.topBar
import kotlinx.android.synthetic.main.layout_base_title.view.*

/**
 * 离校
 */
class LeaveSignActivity : BaseActivity() {

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

    private val materialList = mutableListOf<LocalMedia?>()

    private var materialAdapter: MaterialAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave_sign)
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
        /*val pic = SpUtils.getString(this, MATERIAL_PIC_PATH)
        if (!TextUtils.isEmpty(pic)){
            Glide.with(this)
                .load(pic)
                .apply(RequestOptions.centerCropTransform()).into(ivMaterial)
        }*/
        initRv()
    }


    private fun initView(){
        tvPreview.setOnClickListener { showPreviewDialog() }
        ivMaterial.setOnClickListener { checkPermissions()}
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
                SpUtils.putString(this@LeaveSignActivity, Sign_Time,s.toString())
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
                SpUtils.putString(this@LeaveSignActivity, APPLY_SIGN_TIME,s.toString())
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
                SpUtils.putString(this@LeaveSignActivity, TEACHER_SIGN_TIME,s.toString())
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
                SpUtils.putString(this@LeaveSignActivity, SECRETARY_SIGN_TIME,s.toString())
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
                SpUtils.putString(this@LeaveSignActivity, LEFT_TIME,s.toString())
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
                SpUtils.putString(this@LeaveSignActivity, TRACK,s.toString())
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
        }

        preViewDialog?.show()
    }

    private fun checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE)
        } else {
            openAlbum()
        }
    }

    private fun initRv(){
        val savePics = SpUtils.getString(applicationContext, MATERIAL_PIC_PATH)
        if (!TextUtils.isEmpty(savePics)){
            materialList.addAll(Gson().fromJson<MutableList<LocalMedia>>(savePics,object :TypeToken<MutableList<LocalMedia>>() {}.type))
        }
        ivMaterial.visibility = if (materialList.size < 3) View.VISIBLE else View.GONE
        materialAdapter = MaterialAdapter(this@LeaveSignActivity,materialList)
        with(rvMaterial){
            layoutManager = LinearLayoutManager(this@LeaveSignActivity,LinearLayoutManager.HORIZONTAL,false)
            adapter = materialAdapter
        }
        materialAdapter?.itemClickListener = object : MaterialAdapter.ItemClickListener{
            override fun onItemClick(position: Int) {
                materialList.removeAt(position)
                notifyMaterialAdapter()
            }
        }
    }

    private fun notifyMaterialAdapter(){
        ivMaterial.visibility = if (materialList.size < 3) View.VISIBLE else View.GONE
        materialAdapter?.notifyDataSetChanged()
        val paths = Gson().toJson(materialList)
        SpUtils.putString(applicationContext,MATERIAL_PIC_PATH,paths)
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
                    for (media in result){
                        if (materialList.isEmpty() || materialList.size < 3){
                            materialList.add(media)
                        } else {
                            break
                        }
                    }
                    notifyMaterialAdapter()

                }
                override fun onCancel() {}
            })
    }
}