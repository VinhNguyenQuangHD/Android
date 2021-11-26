package com.example.myandroid;

public class Librarypage_personal_lib {
    int pic,pic2,pic3,pic4;
    String text1,text2,text3;

    public Librarypage_personal_lib(int pic, int pic2, int pic3, int pic4,
                                    String text1, String text2, String text3)
    {
        this.pic = pic;
        this.pic2 = pic2;
        this.pic3 = pic3;
        this.pic4 = pic4;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getPic2() {
        return pic2;
    }

    public void setPic2(int pic2) {
        this.pic2 = pic2;
    }

    public int getPic3() {
        return pic3;
    }

    public void setPic3(int pic3) {
        this.pic3 = pic3;
    }

    public int getPic4() {
        return pic4;
    }

    public void setPic4(int pic4) {
        this.pic4 = pic4;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }
}
