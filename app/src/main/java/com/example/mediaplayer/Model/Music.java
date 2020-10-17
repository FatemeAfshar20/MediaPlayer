package com.example.mediaplayer.Model;

import java.io.File;

public class Music {
    private Integer mId;
    private String mName;
    private String mAddress;

    public Music() {
    }

    public Music(String address) {
        mAddress = address;

        String[] sections=mAddress.split(File.separator);
        String fileNameWithExtension=sections[sections.length-1];
        int lastDotIndex=fileNameWithExtension.lastIndexOf(".");

        mName=fileNameWithExtension.substring(0,lastDotIndex);
    }

    public Music(Integer id, String name, String address) {
        mId = id;
        mName = name;
        mAddress = address;
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
