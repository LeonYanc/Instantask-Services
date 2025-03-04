package com.instantask.service.dto;

public class TaskCardDto {
    private String id;         // must match Task.id
    private String assigneeId; // must match Task.assigneeId
    private int priority;

    public TaskCardDto() {}

    public TaskCardDto(String id, String assigneeId, int priority) {
        this.id = id;
        this.assigneeId = assigneeId;
        this.priority = priority;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
