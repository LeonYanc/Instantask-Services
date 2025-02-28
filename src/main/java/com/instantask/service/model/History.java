package com.instantask.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "histories")
public class History {

    @Id
    private String id;
    private String relatedUser; // references UserAccessID
    private String relatedTask; // references TaskID
    private LocalDateTime issuedTime;
    private String description;

    public History() {
    }

    public History(String id, String relatedUser, String relatedTask,
                   LocalDateTime issuedTime, String description) {
        this.id = id;
        this.relatedUser = relatedUser;
        this.relatedTask = relatedTask;
        this.issuedTime = issuedTime;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelatedUser() {
        return relatedUser;
    }

    public void setRelatedUser(String relatedUser) {
        this.relatedUser = relatedUser;
    }

    public String getRelatedTask() {
        return relatedTask;
    }

    public void setRelatedTask(String relatedTask) {
        this.relatedTask = relatedTask;
    }

    public LocalDateTime getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(LocalDateTime issuedTime) {
        this.issuedTime = issuedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
