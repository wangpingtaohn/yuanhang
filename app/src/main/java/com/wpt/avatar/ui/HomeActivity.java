package com.wpt.avatar.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;

/**
 * Author: wpt
 * Time: 2022/8/5
 *
 * @Desc：
 */
public class HomeActivity extends AppCompatActivity {

    private ImageView ivAvatar;

    private BottomSheetDialog selectDialog;

    private Dialog netPicDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initTopBar();
        initView();
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
        ivAvatar.setOnClickListener(v -> openAlbum());
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
    }
}
