package com.example.myandroid;

import java.util.HashMap;
import java.util.Map;

public class Book_overal {
    String book_name;
    String book_author;
    String book_watch;
    String book_type;
    String book_point;
    String book_date;
    String book_img;

    Book_overal(){}

    public Book_overal(String book_author, String book_name,
                       String book_watch, String book_type, String book_point, String book_date, String book_img) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_watch = book_watch;
        this.book_type = book_type;
        this.book_point = book_point;
        this.book_date = book_date;
        this.book_img = book_img;
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

    public String getBook_watch() {
        return book_watch;
    }

    public void setBook_watch(String book_watch) {
        this.book_watch = book_watch;
    }

    public String getBook_type() {
        return book_type;
    }

    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }

    public String getBook_point() {
        return book_point;
    }

    public void setBook_point(String book_point) {
        this.book_point = book_point;
    }

    public String getBook_date() {
        return book_date;
    }

    public void setBook_date(String book_date) {
        this.book_date = book_date;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    @Override
    public String toString() {
        return "Book_overal{" +
                "book_name='" + book_name + '\'' +
                ", book_author='" + book_author + '\'' +
                ", book_watch='" + book_watch + '\'' +
                ", book_type='" + book_type + '\'' +
                ", book_point='" + book_point + '\'' +
                ", book_date='" + book_date + '\'' +
                ", book_img='" + book_img + '\'' +
                '}';
    }

}
