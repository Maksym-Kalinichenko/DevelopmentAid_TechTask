package com.example.techtask.classes;

import com.google.gson.annotations.SerializedName;

public class Tags {

    @SerializedName("name")
    private String nameTag;

    public Tags(String nameTag) {
        this.nameTag = nameTag;
    }

    public String getNameTag() {
        return nameTag;
    }
}
