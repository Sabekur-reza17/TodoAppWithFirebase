package com.sabekur2017.todoappwithfirebase.models;

public class Todo {
    private String title;
    private String description;
    private String currentUserId;
    private String currentPushId;

    public String getCurrentPushId() {
        return currentPushId;
    }

    public void setCurrentPushId(String currentPushId) {
        this.currentPushId = currentPushId;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }
    // private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


   /* public String getDate() {
        return date;
    }
*/
    /*public void setDate(String date) {
        this.date = date;
    }*/
   /* public HashMap<String,String> toFirebaseObject(){
        HashMap<String,String> todo=new HashMap<>();
        todo.put("name",name);
        todo.put("message",message);
        todo.put("date",date);
        return todo;
    }*/
}
