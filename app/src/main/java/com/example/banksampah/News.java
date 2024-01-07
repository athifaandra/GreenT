package com.example.banksampah;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {
    private String id;
    private String title;
    private String detail;
    private String photoUrl;
    private String userId;
    private String userEmail;

    public News() {

    }

    public News(String id, String title, String detail, String photoUrl, String userId) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.photoUrl = photoUrl;
        this.userId = userId;

    }

    protected News(Parcel in) {
        id = in.readString();
        title = in.readString();
        detail = in.readString();
        photoUrl = in.readString();
        userId = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(detail);
        dest.writeString(photoUrl);
        dest.writeString(userId);
    }
}
