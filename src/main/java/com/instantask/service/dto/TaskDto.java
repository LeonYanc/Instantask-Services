package com.instantask.service.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TaskDto {
    private String id;
    private String name;
    private String assigneeId;      // references UserAccessID
    private String reporterId;      // references UserAccessID
    private String statusColumnId;  // references ColumnID
    private LocalDateTime issuedTime;
    private LocalDateTime dueTime;
    private boolean visibility;
    private List<String> attachedTaskIds; // references TaskID[]
    private List<String> commentIds;      // references CommentID[]
    private List<String> historyIds;      // references HistoryID[]
    private String description;

    public TaskDto() {}

    public TaskDto(String id, String name, String assigneeId, String reporterId,
                   String statusColumnId, LocalDateTime issuedTime, LocalDateTime dueTime,
                   boolean visibility, List<String> attachedTaskIds,
                   List<String> commentIds, List<String> historyIds, String description) {
        this.id = id;
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

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }

    public List<String> getHistoryIds() {
        return historyIds;
    }

    public void setHistoryIds(List<String> historyIds) {
        this.historyIds = historyIds;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
