package com.sabekur2017.todoappwithfirebase.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {
    private String title;
    private String description;
    private String currentUserId;
    private String currentPushId;

    public Todo(Parcel in) {
        title = in.readString();
        description = in.readString();
        currentUserId = in.readString();
        currentPushId = in.readString();
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    public Todo() {

    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(currentUserId);
        dest.writeString(currentPushId);
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
