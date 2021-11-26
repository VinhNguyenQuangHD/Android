package com.example.myandroid;

public class Librarypage_lib_recommend {
    String ten_sach_lib,ten_tac_gia_lib,luot_xem_lib;
    int hinh_sach,icon_luot_xem,icon_them;

    public Librarypage_lib_recommend(String ten_sach_lib, String ten_tac_gia_lib, String luot_xem_lib
            , int hinh_sach, int icon_luot_xem, int icon_them)
    {
        this.ten_sach_lib = ten_sach_lib;
        this.ten_tac_gia_lib = ten_tac_gia_lib;
        this.luot_xem_lib = luot_xem_lib;
        this.hinh_sach = hinh_sach;
        this.icon_luot_xem = icon_luot_xem;
        this.icon_them = icon_them;
    }

    public String getTen_sach_lib() {
        return ten_sach_lib;
    }

    public void setTen_sach_lib(String ten_sach_lib) {
        this.ten_sach_lib = ten_sach_lib;
    }

    public String getTen_tac_gia_lib() {
        return ten_tac_gia_lib;
    }

    public void setTen_tac_gia_lib(String ten_tac_gia_lib) {
        this.ten_tac_gia_lib = ten_tac_gia_lib;
    }

    public String getLuot_xem_lib() {
        return luot_xem_lib;
    }

    public void setLuot_xem_lib(String luot_xem_lib) {
        this.luot_xem_lib = luot_xem_lib;
    }

    public int getHinh_sach() {
        return hinh_sach;
    }

    public void setHinh_sach(int hinh_sach) {
        this.hinh_sach = hinh_sach;
    }

    public int getIcon_luot_xem() {
        return icon_luot_xem;
    }

    public void setIcon_luot_xem(int icon_luot_xem) {
        this.icon_luot_xem = icon_luot_xem;
    }

    public int getIcon_them() {
        return icon_them;
    }

    public void setIcon_them(int icon_them) {
        this.icon_them = icon_them;
    }
}
