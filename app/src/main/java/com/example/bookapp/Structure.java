package com.example.bookapp;

public class Structure {


    public String title,more,img1,subject;
    public int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Structure (String title,  String more, String subject, String img1, int id){

        this.title=title;
        this.more=more;
        this.subject=subject;
        this.img1=img1;
        this.id=id;

    }
}
