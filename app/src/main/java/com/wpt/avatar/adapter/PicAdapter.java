package com.wpt.avatar.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.wpt.avatar.R;

/**
 * Author: wpt
 * Time: 2022/8/5
 *
 * @Desc：
 */
public class PicAdapter extends RecyclerView.Adapter<PicAdapter.MyViewHolder> {

    private String[] pics = {"https://glanceatme24.top/downloads/11_0_122101074_1655191611_5260_353x353_90.jpg",
            "https://glanceatme24.top/downloads/11_1_322101043E90_1_1640758338_353x353_90.jpg",
            "https://glanceatme24.top/downloads/11_1_322101043E90_1_1640758338_353x353_90.jpg",
            "https://glanceatme24.top/downloads/11_2_a7d897fa-13a7-4e7a-82fd-d5e6ee71959b_353x353_90.jpg",
            "https://glanceatme24.top/downloads/11_3_1824672143-1616869390134439936-1616869390134448130-1_353x353_90.jpg",
            "https://glanceatme24.top/downloads/11_4_9150eb35-eee5-4e7d-a354-838d67253942_353x353_90.jpg",
            "https://glanceatme24.top/downloads/11_5_05559c82-c918-43f2-9e53-2094a24d6d4a_353x353_90.jpg",
            "https://glanceatme24.top/downloads/11_6_a216a65f-18ec-4ec8-9810-0b698f28272e_353x353_90.jpg",
            "https://glanceatme24.top/downloads/11_7_1459558331-610314901372792832-610314901372796934-1_353x353_90.jpg",
            "https://glanceatme24.top/downloads/11_8_680261860-596803965633208325-596803965633212443-1_353x353_90.jpg",
            "https://glanceatme24.top/downloads/11_9_1217166911-1953231993686495232-1953231993686499333-1_353x353_90.jpg",
            "https://glanceatme24.top/downloads/11_10_853571627-972854412765470825-972854412765491267-1_353x353_90.jpg",
            "https://glanceatme24.top/downloads/11_11_5acb4132-55fc-4ebd-a897-d2cf642b0cce_353x353_90.jpg"};

    private Context mContext;

    private ItemClickListener listener;

    public PicAdapter(Context context, ItemClickListener listener){
        mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pic,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String url = pics[position];
        if (TextUtils.isEmpty(url)){
            return;
        }
        ImageView ivPic = holder.itemView.findViewById(R.id.ivPic);
        Glide.with(mContext).load(url).into(ivPic);
        holder.itemView.setOnClickListener(v -> {
            if (listener == null){
                return;
            }
            listener.onItemClick(url);
        });

    }

    @Override
    public int getItemCount() {
        return pics.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView){
            super(itemView);
        }
    }

    public interface ItemClickListener {
        void onItemClick(String picUrl);
    }
}
