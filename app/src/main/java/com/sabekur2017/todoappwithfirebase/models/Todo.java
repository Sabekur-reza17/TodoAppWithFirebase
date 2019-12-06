package com.sabekur2017.todoappwithfirebase.models;

import java.util.HashMap;

public class Todo {
    private String name;
    private String message;
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public HashMap<String,String> toFirebaseObject(){
        HashMap<String,String> todo=new HashMap<>();
        todo.put("name",name);
        todo.put("message",message);
        todo.put("date",date);
        return todo;
    }
}
