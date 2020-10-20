package com.example.mediaplayer.Repository;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import com.example.mediaplayer.Model.Music;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MediaRepository {
    public static final String ASSET_MUSIC_FOLDER = "Music";
    public static final String ASSET_IMAGES_FOLDER = "Images";
    private static MediaRepository sInstance;
    private Context mContext;
    private AssetManager mAssetManager;

    private MediaRepository(Context context){
        mContext=context.getApplicationContext();
        mAssetManager=mContext.getAssets();
    }

    public static MediaRepository getInstance(Context context) {
        if (sInstance==null)
            sInstance=new MediaRepository(context);
        return sInstance;
    }

    public List<Music> getMusics() {
        List<Music> musics = new ArrayList<>();

        try{
            String[] fileNames=mAssetManager.list(ASSET_MUSIC_FOLDER);
            for (String fileName: fileNames) {
                String assetPath= ASSET_MUSIC_FOLDER +File.separator+fileName;
                Music sound = new Music(assetPath);
                musics.add(sound);
            }

        }catch (IOException e){

        }

        return musics;
    }

    public List<Drawable> getImages(){
        List<Drawable> images = new ArrayList<>();
        try {
            String[] fileNames=mAssetManager.list(ASSET_IMAGES_FOLDER);
            for (String imageName : fileNames) {
                InputStream inputStream=mAssetManager.open(ASSET_IMAGES_FOLDER+File.separator+imageName);
                Drawable image=Drawable.
                        createFromStream(
                                inputStream,null);

                images.add(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }

/*    AssetManager assetManager = getAssets();
    ImageView imageView = (ImageView) findViewById(R.id.iv_image);
try {
        InputStream ims = assetManager.open("my_image.jpg");
        Drawable d = Drawable.createFromStream(ims, null);
        imageView.setImageDrawable(d);
    } catch (IOException ex) {
        return;
    }*/
}
