package com.example.taskmaster.Models;

public class TaskModel {
    private String title;
    private String body;
    private int state;

    public TaskModel(String title, String body, int state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }

    public TaskModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
