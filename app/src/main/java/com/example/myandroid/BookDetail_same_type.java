package com.example.myandroid;

public class BookDetail_same_type {
    String ten_sach,ten_tac_gia;
    int hinh_anh;

    public BookDetail_same_type(String ten_sach, String ten_tac_gia, int hinh_anh) {
        this.ten_sach = ten_sach;
        this.ten_tac_gia = ten_tac_gia;
        this.hinh_anh = hinh_anh;
    }

    public String getTen_sach() {
        return ten_sach;
    }

    public void setTen_sach(String ten_sach) {
        this.ten_sach = ten_sach;
    }

    public String getTen_tac_gia() {
        return ten_tac_gia;
    }

    public void setTen_tac_gia(String ten_tac_gia) {
        this.ten_tac_gia = ten_tac_gia;
    }

    public int getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(int hinh_anh) {
        this.hinh_anh = hinh_anh;
    }
}
