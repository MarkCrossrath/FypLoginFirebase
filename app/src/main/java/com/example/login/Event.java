package com.example.login;

public class Event {

   private String name;
   private String image;
   private String description;
   private String location;
   private String time;

    public Event() {

    }

    public Event(String name, String image, String description, String location, String time) {
        this.name = name;
        this.image = image;
       this.description = description;
       this.location = location;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}