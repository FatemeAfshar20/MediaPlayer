package com.example.mediaplayer.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaplayer.R;

import java.util.List;

public class SingerImagesAdapter extends RecyclerView.Adapter<SingerImagesAdapter.Holder> {
    private List<Drawable> mImages;
    private Context mContext;

    public List<Drawable> getImages() {
        return mImages;
    }

    public void setImages(List<Drawable> images) {
        mImages = images;
    }

    public SingerImagesAdapter(List<Drawable> images, Context context) {
        mImages = images;
        mContext = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).
                inflate(R.layout.item_singer_images,
                        parent,
                        false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.bind(mImages.get(position));
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private AppCompatImageView mImageView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            findViews(itemView);
        }

        private void findViews(@NonNull View itemView) {
            mImageView=itemView.findViewById(R.id.img_singer_view);
        }

        public void bind(Drawable image){
            mImageView.setImageDrawable(image);
        }
    }
}
