package com.wpt.avatar.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.config.SelectModeConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.wpt.avatar.R;
import com.wpt.avatar.adapter.PicAdapter;
import com.wpt.avatar.utils.SpUtils;

import java.util.ArrayList;

/**
 * Author: wpt
 * Time: 2022/8/5
 *
 * @Desc：
 */
public class HomeActivity extends AppCompatActivity {

    private static final String PIC_KEY = "picKey";

    private static final String USERNAME = "userName";

    private static final String USER_NUMBER = "userNumber";

    private ImageView ivAvatar;

    private ImageView ivLogo;

    private View rootView;

    private boolean isEnable;

    private EditText etName;

    private EditText etNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initTopBar();
        initView();
        initData();
        initAnimator();
    }

    private void initTopBar(){
        ImageView ivLeft = findViewById(R.id.ivBack);
        ImageView ivMore = findViewById(R.id.ivMore);
        ivMore.setVisibility(View.GONE);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvBack = findViewById(R.id.tvBack);
        tvTitle.setText("电子证件");
        tvBack.setOnClickListener(v -> finish());
        ivLeft.setOnClickListener(v -> finish());
    }

    private void initView(){
        ivAvatar = findViewById(R.id.sivAvatar);
        ivLogo = findViewById(R.id.ivLogo);
        rootView = findViewById(R.id.rootView);
        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        ivAvatar.setOnClickListener(v -> openAlbum());

        rootView.setOnLongClickListener(v -> {
            setEditEnable();
            return false;
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    return;
                }
                SpUtils.putString(HomeActivity.this,USERNAME,s.toString());
            }
        });
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    return;
                }
                SpUtils.putString(HomeActivity.this,USER_NUMBER,s.toString());
            }
        });
    }

    private void initData(){
        String picPath = SpUtils.getString(this,PIC_KEY);
        String userName = SpUtils.getString(this,USERNAME);
        String userNumber = SpUtils.getString(this,USER_NUMBER);
        showAvatar(picPath);
        if (!TextUtils.isEmpty(userName)){
            etName.setText(userName);
        }
        if (!TextUtils.isEmpty(userNumber)){
            etNumber.setText(userNumber);
        }
    }


    private void setEditEnable(){
        isEnable = !isEnable;
        etName.setFocusable(isEnable);
        etNumber.setFocusable(isEnable);
        etName.setFocusableInTouchMode(isEnable);
        etNumber.setFocusableInTouchMode(isEnable);
    }

    private void initAnimator(){
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        ivLogo.startAnimation(fadeIn);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivLogo.startAnimation(fadeOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivLogo.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void openAlbum(){
        PictureSelector.create(this)
                .openSystemGallery(SelectMimeType.ofImage())
                .setSelectionMode(SelectModeConfig.SINGLE)
                .forSystemResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(ArrayList<LocalMedia> result) {
                        if (result == null || result.isEmpty()){
                            return;
                        }
                        LocalMedia localMedia = result.get(0);
                        showAvatar(localMedia.getPath());
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }

    private void openCamera(){
        PictureSelector.create(this)
                .openCamera(SelectMimeType.ofImage())
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(ArrayList<LocalMedia> result) {
                        if (result == null || result.isEmpty()){
                            return;
                        }
                        LocalMedia localMedia = result.get(0);
                        showAvatar(localMedia.getPath());
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }

    private void showAvatar(String picPath){
        if (TextUtils.isEmpty(picPath) || ivAvatar == null){
            return;
        }
        Glide.with(this).load(picPath).into(ivAvatar);
        SpUtils.putString(this, PIC_KEY,picPath);
    }

    @Override
    public void finish() {
        super.finish();
        ivLogo.clearAnimation();
    }
}
