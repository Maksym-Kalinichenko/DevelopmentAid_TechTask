package com.example.techtask.classes;

import com.google.gson.annotations.SerializedName;

public class JSONTags {

    @SerializedName("items")
    private Tags[] tagsArray;

    public Tags[] getTagsArray() {
        return tagsArray;
    }

    public void setTagsArray(Tags[] tagsArray) {
        this.tagsArray = tagsArray;
    }

}
