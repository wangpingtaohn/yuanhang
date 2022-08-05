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
        ivLeft.setVisibility(View.GONE);
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("我的");
    }

    private void initView(){
        ivAvatar = findViewById(R.id.sivAvatar);
        ivAvatar.setOnClickListener(v -> showDialog());
    }

    private void showDialog(){
        if (selectDialog == null){
            selectDialog = new BottomSheetDialog(this);
            selectDialog.setContentView(R.layout.dialog_select_pic);
            Button btnAlbum = selectDialog.findViewById(R.id.btnAlbum);
            Button btnCamera = selectDialog.findViewById(R.id.btnCamera);
            Button btnNet = selectDialog.findViewById(R.id.btnNet);
            Button btnCancel = selectDialog.findViewById(R.id.btnCancel);

            btnAlbum.setOnClickListener(v -> {
                selectDialog.dismiss();
                openAlbum();
            });
            btnCamera.setOnClickListener(v -> {
                selectDialog.dismiss();
                openCamera();
            });
            btnNet.setOnClickListener(v -> {
                selectDialog.dismiss();
                showNetPic();
            });
            btnCancel.setOnClickListener(v -> selectDialog.dismiss());
        }
        selectDialog.show();
    }

    private void showNetPic(){
        if (netPicDialog == null){
            netPicDialog = new BottomSheetDialog(this);
            netPicDialog.setContentView(R.layout.dialog_pic_rv);
            RecyclerView rv = netPicDialog.findViewById(R.id.rvNet);
            GridLayoutManager lp = new GridLayoutManager(this,2);
//            LinearLayoutManager lp = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            rv.setLayoutManager(lp);
            PicAdapter adapter = new PicAdapter(this, picUrl -> {
                netPicDialog.dismiss();
                showAvatar(picUrl);
            });
            rv.setAdapter(adapter);
        }
        netPicDialog.show();

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
