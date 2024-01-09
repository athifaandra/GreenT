package com.example.banksampah;

public class PaymentItem {

    private int berat;
    private String items;
    private Long price;
    private String data;
    private String status;
    private Long date;

    public PaymentItem() {
        // Default constructor required for DataSnapshot.getValue(OrganicItem.class)
    }

    public PaymentItem(int berat, String items, Long price, String data, String status, Long date) {
        this.berat = berat;
        this.items = items;
        this.price = price;
        this.data = data;
        this.status = status;
        this.date = date;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}