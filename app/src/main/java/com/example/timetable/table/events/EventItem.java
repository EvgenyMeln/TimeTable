package com.example.timetable.table.events;


import java.io.Serializable;

public class EventItem implements Serializable {

    String name;
    String time;
    String comment;
    String homework;
    int day;
    int id;

    public EventItem(int id, String name, String time, String comment, String homework){
        this.id = id;
        this.name = name;
        this.time = time;
        this.comment = comment;
        this.homework = homework;
    }

    public String getName(){
        return this.name;
    }

    public String getTime(){
        return this.time;
    }

    public String getComment(){
        return this.comment;
    }

    public int getId(){
        return this.id;
    }

    public String getHomework(){
        return this.homework;
    }

}