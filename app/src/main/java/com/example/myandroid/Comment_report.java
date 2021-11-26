package com.example.myandroid;

public class Comment_report {
    String username,book_name,content,type;

    public Comment_report(){}

    public Comment_report(String username, String book_name, String content, String type) {
        this.username = username;
        this.book_name = book_name;
        this.content = content;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
