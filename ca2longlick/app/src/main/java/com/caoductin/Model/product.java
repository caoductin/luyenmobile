package com.caoductin.Model;

public class product {
    int masp;
    String TenSp;
    double Gia;

    public product(int masp, String tenSp, double gia) {
        this.masp = masp;
        TenSp = tenSp;
        Gia = gia;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTenSp() {
        return TenSp;
    }

    public void setTenSp(String tenSp) {
        TenSp = tenSp;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double gia) {
        Gia = gia;
    }
}
