package com.example.banksampah;

public class alamatsg {
    private String phonum;
    private String address;
    private String subdis;
    private String ward;
    private String postalcode;

    public alamatsg() {
    }

    public alamatsg(String phonum, String address, String subdis, String ward, String postalcode) {
        this.phonum = phonum;
        this.address = address;
        this.subdis = subdis;
        this.ward = ward;
        this.postalcode = postalcode;
    }

    public String getPhonum() {
        return phonum;
    }

    public String getAddress() {
        return address;
    }

    public String getSubdis() {
        return subdis;
    }

    public String getWard() {
        return ward;
    }

    public String getPostalcode() {
        return postalcode;
    }
}