package com.example.login;

public class Event {

    private String title;
    private String image;
    private String description;
    private String location;
    private String date;

    public Event(String s , String toString){
        //do not delete this

    }

    public Event(){

    }

    public Event(String title , String image , String description , String location , String date) {



        this.title = title;
        this.image = image;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}