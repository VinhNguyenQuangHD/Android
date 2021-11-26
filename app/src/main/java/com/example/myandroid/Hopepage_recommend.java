package com.example.myandroid;

import android.widget.ImageButton;
import android.widget.ImageView;

public class Hopepage_recommend {
    String book_name,book_author,book_view;

    public Hopepage_recommend(String book_name, String book_author, String book_view) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_view = book_view;
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
}
