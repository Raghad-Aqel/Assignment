package com.example.assignment;

public class Task {
    private String taskName;
    private String taskDescription;
    private boolean isDone;

    public Task() {
    }

    public Task(String taskName, String taskDescription, boolean isDone) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString(){return getTaskName()+"\n" +getTaskDescription() +"  --> isDone? "+isDone(); }

}
