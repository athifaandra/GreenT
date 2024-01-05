package com.example.banksampah;

public class SetterGetter3 {

    String titlekiri;
    String imgkiri;
    String titlekanan;
    String imgkanan;
    String panah;

    public SetterGetter3(String titlekiri, String imgkiri, String titlekanan, String imgkanan, String panah){
        this.titlekiri = titlekiri;
        this.imgkiri = imgkiri;
        this.titlekanan = titlekanan;
        this.imgkanan = imgkanan;
        this.panah = panah;
    }

    public String getTitlekiri() {
        return titlekiri;
    }

    public void setTitlekiri(String titlekiri) {
        this.titlekiri = titlekiri;
    }

    public String getImgkiri() {
        return imgkiri;
    }

    public void setImgkiri(String imgkiri) {
        this.imgkiri = imgkiri;
    }

    public String getTitlekanan() {
        return titlekanan;
    }

    public void setTitlekanan(String titlekanan) {
        this.titlekanan = titlekanan;
    }

    public String getImgkanan() {
        return imgkanan;
    }

    public void setImgkanan(String imgkanan) {
        this.imgkanan = imgkanan;
    }

    public String getPanah() {
        return panah;
    }

    public void setPanah(String panah) {
        this.panah = panah;
    }
}
