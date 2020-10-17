package com.example.mediaplayer.Model;

import java.io.File;

public class Music {
    private Integer mId;
    private String mName;
    private String mAddress;

    public Music(Integer id) {
        mId = id;
    }

    public Music(String address) {
        mAddress=address;
        String[] sections=address.split(File.separator);
        String musicName=sections[sections.length-1];

        mName = musicName.substring(0,musicName.lastIndexOf("."));
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }
}
