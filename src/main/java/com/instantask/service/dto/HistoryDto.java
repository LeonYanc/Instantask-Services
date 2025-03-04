package com.instantask.service.dto;

import java.time.LocalDateTime;

public class HistoryDto {
    private String id;
    private String relatedUser; // references UserAccessID
    private String relatedTask; // references TaskID
    private LocalDateTime issuedTime;
    private String description;

    public HistoryDto() {}

    public HistoryDto(String id, String relatedUser, String relatedTask,
                      LocalDateTime issuedTime, String description) {
        this.id = id;
        this.relatedUser = relatedUser;
        this.relatedTask = relatedTask;
        this.issuedTime = issuedTime;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(LocalDateTime issuedTime) {
        this.issuedTime = issuedTime;
    }

    public String getRelatedTask() {
        return relatedTask;
    }

    public void setRelatedTask(String relatedTask) {
        this.relatedTask = relatedTask;
    }

    public String getRelatedUser() {
        return relatedUser;
    }

    public void setRelatedUser(String relatedUser) {
        this.relatedUser = relatedUser;
    }

    // Getters and setters...
}
