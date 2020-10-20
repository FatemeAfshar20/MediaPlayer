package com.example.mediaplayer.Controller.Activity;

import android.media.MediaPlayer;

import androidx.fragment.app.Fragment;

import com.example.mediaplayer.Controller.Fragment.MediaFragment;
import com.example.mediaplayer.Controller.Fragment.SingerImagesFragment;
import com.example.mediaplayer.R;
import com.example.mediaplayer.SingleFragmentActivity;

public class MediaActivity extends SingleFragmentActivity
implements MediaFragment.TopFragment {
    @Override
    public Fragment getFragment() {
        return MediaFragment.newInstance();
    }

    @Override
    public void addFragmentToTop() {
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.fragment_images_singers,
                        SingerImagesFragment.newInstance()).
                commit();
    }
}