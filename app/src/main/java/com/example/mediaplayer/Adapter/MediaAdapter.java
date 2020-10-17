package com.example.mediaplayer.Adapter;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaplayer.Model.Music;
import com.example.mediaplayer.Play.PlayMusic;
import com.example.mediaplayer.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.Holder> {
    private List<Music> mMusicList;
    private List<Integer> mIntegers;
    private Context mContext;

    public MediaAdapter(List<Music> musicList, Context context) {
        mMusicList = musicList;
        mContext = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(
                R.layout.item_music,parent,false);

        return new Holder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.bind(mMusicList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMusicList.size();
    }

    class Holder extends RecyclerView.ViewHolder implements Runnable{
        private TextView mMusicName;
        private ImageButton mBtnPlay,mBtnPause,mBtnStop;
        private Music mMusic;
        private MediaPlayer mMediaPlayer=new MediaPlayer();
        private SeekBar mSeekBar;

        public Holder(@NonNull View itemView) {
            super(itemView);
            findView(itemView);
            setListener();
        }

        private void findView(View view){
            mMusicName =view.findViewById(R.id.music_name);
            mBtnPlay=view.findViewById(R.id.btn_play);
            mBtnPause=view.findViewById(R.id.btn_pause);
            mSeekBar=view.findViewById(R.id.seekbar);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void bind(Music music){
            mMusic=music;
            mMusicName.setText(mMusic.getName());
            try {
                AssetFileDescriptor descriptor=mContext.getAssets().openFd(mMusic.getAddress());
                mMediaPlayer.setDataSource(descriptor);
                mMediaPlayer.prepare();
                mMediaPlayer.setVolume(0.5f, 0.5f);
                mMediaPlayer.setLooping(false);
                mSeekBar.setMax(mMediaPlayer.getDuration());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void setListener(){
            mBtnPlay.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                        mMediaPlayer.start();
                    new Thread(Holder.this).start();
                }
            });

            mBtnPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMediaPlayer.pause();
                }
            });

            mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int x = (int) Math.ceil(progress / 1000f);

                    if (x== 0 && mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
                       seekBar.setProgress(0);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                        mMediaPlayer.seekTo(seekBar.getProgress());
                    }
                }
            });
        }

        @Override
        public void run() {

            int currentPosition = mMediaPlayer.getCurrentPosition();
            int total = mMediaPlayer.getDuration();


            while (mMediaPlayer != null && mMediaPlayer.isPlaying() && currentPosition < total) {
                try {
                    Thread.sleep(1000);
                    currentPosition = mMediaPlayer.getCurrentPosition();
                } catch (InterruptedException e) {
                    return;
                } catch (Exception e) {
                    return;
                }

                mSeekBar.setProgress(currentPosition);

            }
        }
    }
}
