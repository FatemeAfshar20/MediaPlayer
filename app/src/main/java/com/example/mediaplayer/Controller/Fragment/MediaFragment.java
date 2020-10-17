package com.example.mediaplayer.Controller.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediaplayer.Adapter.MediaAdapter;
import com.example.mediaplayer.Controller.Activity.MadiaActivity;
import com.example.mediaplayer.R;
import com.example.mediaplayer.Repository.MediaRepository;

public class MediaFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MediaRepository mRepository;

    public MediaFragment() {
        // Required empty public constructor
    }

    public static MediaFragment newInstance() {
        MediaFragment fragment = new MediaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository=MediaRepository.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_media,
                container, false);

        mRecyclerView=view.findViewById(R.id.recycler_view);
        MediaAdapter adapter=new MediaAdapter(mRepository.getMusics(),getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);

        return view;
    }
}