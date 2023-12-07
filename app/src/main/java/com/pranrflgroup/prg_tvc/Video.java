package com.pranrflgroup.prg_tvc;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Video {
    @SerializedName("fsource")
    private String fsource;

    public Video(String fsource) {
        this.fsource = fsource;
    }

    public String getFsource() {
        return fsource;
    }

    public void setFsource(String fsource) {
        this.fsource = fsource;
    }
}
