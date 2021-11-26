package com.example.myandroid;

public class Book_detail {
    String book_name;
    String book_author;
    String book_view;
    String book_point;
    String book_datetime;
    String book_img;
    String book_src_link;
    String book_type;

    public Book_detail(){}

    public Book_detail(String book_name,
                       String book_author, String book_view, String book_point,
                       String book_datetime, String book_img, String book_src_link,String book_type
                       ) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_view = book_view;
        this.book_point = book_point;
        this.book_datetime = book_datetime;
        this.book_img = book_img;
        this.book_src_link = book_src_link;
        this.book_type = book_type;
    }

    public String getBook_type() {
        return book_type;
    }

    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_view() {
        return book_view;
    }

    public void setBook_view(String book_view) {
        this.book_view = book_view;
    }

    public String getBook_point() {
        return book_point;
    }

    public void setBook_point(String book_point) {
        this.book_point = book_point;
    }

    public String getBook_datetime() {
        return book_datetime;
    }

    public void setBook_datetime(String book_datetime) {
        this.book_datetime = book_datetime;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    public String getBook_src_link() {
        return book_src_link;
    }

    public void setBook_src_link(String book_src_link) {
        this.book_src_link = book_src_link;
    }

}
