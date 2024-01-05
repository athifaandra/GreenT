package com.example.banksampah;

public class TipeSampah {
    private String jenisSampah;
    private int logoSampah;

    public TipeSampah(String jenisSampah, int logoSampah) {
        this.jenisSampah = jenisSampah;
        this.logoSampah = logoSampah;
    }

    public String getJenisSampah() {
        return jenisSampah;
    }

    public void setJenisSampah(String jenisSampah) {
        this.jenisSampah = jenisSampah;
    }

    public int getLogoSampah() {
        return logoSampah;
    }

    public void setLogoSampah(int logoSampah) {
        this.logoSampah = logoSampah;
    }
}
