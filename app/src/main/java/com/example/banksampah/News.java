package com.example.banksampah;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {
    private String id;
    private String title;
    private String detail;
    private String photoUrl;

    public News() {

    }

    public News(String id, String title, String detail, String photoUrl) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.photoUrl = photoUrl;
    }

    protected News(Parcel in) {
        id = in.readString();
        title = in.readString();
        detail = in.readString();
        photoUrl = in.readString();
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
    }
}
