package com.example.techtask.classes;

import com.google.gson.annotations.SerializedName;

public class Users {

    public Users() {
    }

    @SerializedName("display_name")
    private String name;

    @SerializedName("location")
    private String location;

    @SerializedName("answer_count")
    private int answer;

    @SerializedName("question_count")
    private int question;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("link")
    private String profile;

    @SerializedName("profile_image")
    private String avatar;

    @SerializedName("reputation")
    private int reputation;

    public String tag = "0";

    public int getReputation() {
        return reputation;
    }

    public String getTag() {
        return tag;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getAnswer() {
        return answer;
    }

    public int getQuestion() {
        return question;
    }

    public String getProfile() {
        return profile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }
}
