package com.example.mediaplayer.Repository;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

import com.example.mediaplayer.Model.Music;
import com.example.mediaplayer.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MediaRepository {
    public static final String ASSET_FOLDER = "Music";
    private static MediaRepository sInstance;
    private Context mContext;
    private MediaPlayer mMediaPlayer;


    private MediaRepository(Context context){
        mContext=context.getApplicationContext();
    }

    public static MediaRepository getInstance(Context context) {
        if (sInstance==null)
            sInstance=new MediaRepository(context);
        return sInstance;
    }

    public List<Music> getMusics() {
        List<Music> musics = new ArrayList<>();

        AssetManager assetManager = mContext.getAssets();
        try{
            String[] fileNames=assetManager.list(ASSET_FOLDER);
            for (String fileName: fileNames) {

                String assetPath = ASSET_FOLDER + File.separator + fileName;
                Music sound = new Music(assetPath);
                musics.add(sound);
            }

        }catch (IOException e){

        }

        return musics;
    }
}
