package com.instantask.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "tasks")
public class Task {

    @Id
    private String id;
    private String name;
    private String assigneeId;
    private String reporterId;
    private String statusColumnId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime issuedTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dueTime;

    private boolean visibility;
    private List<String> attachedTaskIds;
    private List<String> commentIds;
    private List<String> historyIds;
    private String description;

    public Task() {
    }

    public Task(String name, String assigneeId, String reporterId, String statusColumnId,
                LocalDateTime issuedTime, LocalDateTime dueTime, boolean visibility,
                List<String> attachedTaskIds, List<String> commentIds,
                List<String> historyIds, String description) {
        this.name = name;
        this.assigneeId = assigneeId;
        this.reporterId = reporterId;
        this.statusColumnId = statusColumnId;
        this.issuedTime = issuedTime;
        this.dueTime = dueTime;
        this.visibility = visibility;
        this.attachedTaskIds = attachedTaskIds;
        this.commentIds = commentIds;
        this.historyIds = historyIds;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    public String getStatusColumnId() {
        return statusColumnId;
    }

    public void setStatusColumnId(String statusColumnId) {
        this.statusColumnId = statusColumnId;
    }

    public LocalDateTime getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(LocalDateTime issuedTime) {
        this.issuedTime = issuedTime;
    }

    public LocalDateTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public List<String> getAttachedTaskIds() {
        return attachedTaskIds;
    }

    public void setAttachedTaskIds(List<String> attachedTaskIds) {
        this.attachedTaskIds = attachedTaskIds;
    }

    public List<String> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<String> commentIds) {
        this.commentIds = commentIds;
    }

    public List<String> getHistoryIds() {
        return historyIds;
    }

    public void setHistoryIds(List<String> historyIds) {
        this.historyIds = historyIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Task to JSON", e);
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", assigneeId='" + assigneeId + '\'' +
                ", reporterId='" + reporterId + '\'' +
                ", statusColumnId='" + statusColumnId + '\'' +
                ", issuedTime=" + issuedTime +
                ", dueTime=" + dueTime +
                ", visibility=" + visibility +
                ", attachedTaskIds=" + attachedTaskIds +
                ", commentIds=" + commentIds +
                ", historyIds=" + historyIds +
                ", description='" + description + '\'' +
                '}';
    }
}
