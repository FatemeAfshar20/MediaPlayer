package com.example.mediaplayer.Controller.Activity;

import androidx.fragment.app.Fragment;

import com.example.mediaplayer.Controller.Fragment.MediaFragment;
import com.example.mediaplayer.SingleFragmentActivity;

public class MadiaActivity extends SingleFragmentActivity {
    @Override
    public Fragment getFragment() {
        return MediaFragment.newInstance();
    }

}