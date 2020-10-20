package com.example.mediaplayer.Controller.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaplayer.Adapter.SingerImagesAdapter;
import com.example.mediaplayer.R;
import com.example.mediaplayer.Repository.MediaRepository;

public class SingerImagesFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MediaRepository mRepository;

    public SingerImagesFragment() {
        // Required empty public constructor
    }

    public static SingerImagesFragment newInstance() {
        SingerImagesFragment fragment = new SingerImagesFragment();
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
        View view= inflater.inflate(R.layout.fragment_songer_images, container, false);
        findViews(view);

        setupAdapter();
        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.singer_recycler_view);
    }

    private void setupAdapter() {
        mRecyclerView.setLayoutManager(
                new GridLayoutManager(
                        getContext(),
                        2));
        SingerImagesAdapter adapter = new SingerImagesAdapter(
                mRepository.getImages(), getContext());
        mRecyclerView.setAdapter(adapter);
    }
}