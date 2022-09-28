package com.example.techtask.classes;

import com.google.gson.annotations.SerializedName;

public class JSON {
    @SerializedName("items")
    private Users[] usersArray;

    public Users[] getUsersArray() {
        return usersArray;
    }

    public void setUsersArray(Users[] usersArray) {
        this.usersArray = usersArray;
    }
}
