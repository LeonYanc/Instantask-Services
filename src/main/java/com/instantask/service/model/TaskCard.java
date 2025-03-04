package com.instantask.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "taskCards")
public class TaskCard {

    @Id
    private String id;
    private String assigneeId;
    private int priority;

    public TaskCard() {
    }

    public TaskCard(String id, String assigneeId, int priority) {
        this.id = id;
        this.assigneeId = assigneeId;
        this.priority = priority;
    }

    // Constructor to create a TaskCard from a Task
    public TaskCard(Task task, int priority) {
        this.id = task.getId();
        this.assigneeId = task.getAssigneeId();
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    // Sync this TaskCard with a Task
    public void syncWithTask(Task task) {
        this.id = task.getId();
        this.assigneeId = task.getAssigneeId();
    }

    @Override
    public String toString() {
        return "TaskCard{" +
                "id='" + id + '\'' +
                ", assigneeId='" + assigneeId + '\'' +
                ", priority=" + priority +
                '}';
    }
}
