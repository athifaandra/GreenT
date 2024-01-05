package com.example.banksampah;

public class SetterGetter2 {

    String title2;
    String img2;
    String panah;
    String desc;

    public SetterGetter2(String title2, String img2, String panah, String desc){
        this.title2 = title2;
        this.img2 = img2;
        this.panah = panah;
        this.desc = desc;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getPanah() {
        return panah;
    }

    public void setPanah(String panah) {
        this.panah = panah;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
