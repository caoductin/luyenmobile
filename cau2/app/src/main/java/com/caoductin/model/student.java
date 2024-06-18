package com.caoductin.model;

public class student {
    int masv;
    String tensv;
    String lop;

    public int getMasv() {
        return masv;
    }

    public student(int masv, String tensv, String lop) {
        this.masv = masv;
        this.tensv = tensv;
        this.lop = lop;
    }

    public void setMasv(int masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
