package com.example.banksampah;

public class RecycleItem {
    private String titleLeft;
    private String titleRight;
    private String imageLeftResource;

    private String imageRightResource;

    private RecycleItem() {

    }

    public String getTitleLeft() {
        return titleLeft;
    }

    public void setTitleLeft(String titleLeft) {
        this.titleLeft = titleLeft;
    }

    public String getTitleRight() {
        return titleRight;
    }

    public void setTitleRight(String titleRight) {
        this.titleRight = titleRight;
    }

    public String getImageLeftResource() {
        return imageLeftResource;
    }

    public void setImageLeftResource(String imageLeftResource) {
        this.imageLeftResource = imageLeftResource;
    }

    public String getImageRightResource() {
        return imageRightResource;
    }

    public void setImageRightResource(String imageRightResource) {
        this.imageRightResource = imageRightResource;
    }



    public RecycleItem(String titleLeft, String titleRight, String imageLeftResource, String imageRightResource) {
        this.titleLeft = titleLeft;
        this.titleRight = titleRight;
        this.imageLeftResource = imageLeftResource;
        this.imageRightResource = imageRightResource;
    }


}

