package com.wenda.kamus;

public class Kamus {

    private int id;
    private String istilah;
    private String arti;
    private byte[] gambar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIstilah() {
        return istilah;
    }

    public void setIstilah(String istilah) {
        this.istilah = istilah;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }
}
