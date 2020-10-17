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

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.bind(mMusicList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMusicList.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        private TextView mMusicName;
        private ImageButton mBtnPlay,mBtnPause,mBtnStop;
        private Music mMusic;
        private MediaPlayer mMediaPlayer=new MediaPlayer();
        private Integer mInteger;
        private SeekBar mSeekBar;
        private PlayMusic mPlayMusic;

        public Holder(@NonNull View itemView) {
            super(itemView);
            findView(itemView);
            setListener();
        }

        private void findView(View view){
            mMusicName =view.findViewById(R.id.music_name);
            mBtnPlay=view.findViewById(R.id.btn_play);
            mBtnPause=view.findViewById(R.id.btn_pause);
            mBtnStop=view.findViewById(R.id.btn_stop);
            mSeekBar=view.findViewById(R.id.seekbar);
        }

        private void bind(Music music){
            mMusic=music;
            mMusicName.setText(mMusic.getName());
            mPlayMusic=new PlayMusic(mMediaPlayer,mContext,mMusic.getAddress());
        }

        private void setListener(){
            mBtnPlay.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                   try {
                       AssetFileDescriptor descriptor = mContext.getAssets().openFd("Music"+ File.separator+mMusic.getName()+".mp3");
                       mMediaPlayer.setDataSource(descriptor);
                       descriptor.close();

                       mMediaPlayer.prepare();
                       mMediaPlayer.setVolume(0.5f, 0.5f);
                       mMediaPlayer.setLooping(false);
                       mSeekBar.setMax(mMediaPlayer.getDuration());
                   }catch (Exception e){
                       e.printStackTrace();
                   }
                }
            });

            mBtnPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMediaPlayer.pause();
                }
            });

            mBtnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMediaPlayer.stop();
                }
            });

            mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int x = (int) Math.ceil(progress / 1000f);

                    if (x== 0 && mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
                        mPlayMusic.clearMediaPlayer();
                        mSeekBar.setProgress(0);
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

    }
}
